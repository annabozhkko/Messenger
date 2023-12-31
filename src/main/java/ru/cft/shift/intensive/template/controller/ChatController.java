package ru.cft.shift.intensive.template.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.cft.shift.intensive.template.dto.ChatDto;
import ru.cft.shift.intensive.template.dto.MessageDto;
import ru.cft.shift.intensive.template.dto.UsernameDto;
import ru.cft.shift.intensive.template.service.MessagesService;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@RestController
@RequestMapping("api/chats")
@Tag(name = "api.chat.tag.name", description = "api.chat.tag.description")
public class ChatController {
    private final MessagesService messagesService;

    @Autowired
    public ChatController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @Operation(summary = "api.chat.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.chat.api-responses.200.description"),
            @ApiResponse(responseCode = "500", description = "api.server.error", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))})
    })
    @GetMapping("{username}")
    public ResponseEntity<List<UsernameDto>> getChats(@PathVariable @Size(min = 5, max = 32) String username){
        return ResponseEntity.ok(messagesService.getChats(username));
    }

    @Operation(summary = "api.chat.messages.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.chat.messages.api-responses.200.description"),
            @ApiResponse(responseCode = "500", description = "api.server.error", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))})
    })
    @GetMapping()
    public ResponseEntity<List<MessageDto>> getMessages(@RequestBody @Valid ChatDto chat, @RequestParam("date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return ResponseEntity.ok(messagesService.getMessages(chat.user1(), chat.user2(), date));
    }

    @Operation(summary = "api.chat.messages.send.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.chat.messages.send.api-responses.200.description"),
            @ApiResponse(responseCode = "500", description = "api.server.error", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))})
    })
    @PostMapping()
    public ResponseEntity<Void> sendMessage(@RequestBody @Valid MessageDto message){
        messagesService.sendMessage(message);
        return ResponseEntity.ok().build();
    }

}
