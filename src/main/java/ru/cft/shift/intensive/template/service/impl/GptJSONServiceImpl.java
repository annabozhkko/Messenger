package ru.cft.shift.intensive.template.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.cft.shift.intensive.template.service.GptJSONService;

import java.util.List;

public class GptJSONServiceImpl implements GptJSONService {
    @JsonProperty("choices")
    private List<Choice> choices;

    public String getAnswer() {
        if (choices != null && !choices.isEmpty()) {
            return choices.get(0).getText().replace("\n", "").replace("\r", "").trim();
        }
        return null;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Choice {
        @JsonProperty("text")
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
