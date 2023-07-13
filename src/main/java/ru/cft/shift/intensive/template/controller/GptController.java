package ru.cft.shift.intensive.template.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.cft.shift.intensive.template.service.impl.GptJSONServiceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/gpt")
public class GptController {
    private static final String OPENAI_API_URL = "https://api.openai.com/v1/engines/text-davinci-003/completions";
    private static final String OPENAI_API_KEY = "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";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public GptController() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    @PostMapping("/short")
    public ResponseEntity<?> shortCompletion(@RequestBody(required = false) String requestBody) {
        if (requestBody == null || requestBody.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request format");
        }

        try {
            Map<String, Object> requestBodyMap = objectMapper.readValue(requestBody, new TypeReference<Map<String, Object>>() {});
            if (!requestBodyMap.containsKey("prompt")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request format: 'prompt' field is missing");
            }
            String prompt = (String) requestBodyMap.get("prompt");
            return completeWithMaxTokens(prompt, 100);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request format");
        }
    }

    @PostMapping("/long")
    public ResponseEntity<?> longCompletion(@RequestBody(required = false) String requestBody) {
        if (requestBody == null || requestBody.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request format");
        }

        try {
            Map<String, Object> requestBodyMap = objectMapper.readValue(requestBody, new TypeReference<Map<String, Object>>() {});
            if (!requestBodyMap.containsKey("prompt")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request format: 'prompt' field is missing");
            }
            String prompt = (String) requestBodyMap.get("prompt");
            return completeWithMaxTokens(prompt, 1000);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request format");
        }
    }

    private ResponseEntity<?> completeWithMaxTokens(String prompt, int maxTokens) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(OPENAI_API_KEY);

        Map<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("prompt", prompt);
        requestBodyMap.put("max_tokens", maxTokens);
        String requestBody;

        try {
            requestBody = objectMapper.writeValueAsString(requestBodyMap);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                OPENAI_API_URL,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            try {
                GptJSONServiceImpl gptJSONServiceImpl = objectMapper.readValue(responseEntity.getBody(), GptJSONServiceImpl.class);
                String answer = gptJSONServiceImpl.getAnswer();

                // Создаем объект GptResponseWrapper с ответом
                GptResponseWrapper responseWrapper = new GptResponseWrapper(answer);

                return ResponseEntity.ok(responseWrapper);
            } catch (IOException e) {
                System.out.println("Error occurred during deserialization: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
            }
        } else {
            return ResponseEntity.status(responseEntity.getStatusCode()).body("Error occurred");
        }
    }

    public static class GptResponseWrapper {
        private String answer;

        public GptResponseWrapper(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }
}
