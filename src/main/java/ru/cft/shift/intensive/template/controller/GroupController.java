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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.cft.shift.intensive.template.dto.GroupDto;
import ru.cft.shift.intensive.template.dto.MessageDto;
import ru.cft.shift.intensive.template.service.GroupsService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@RestController
@RequestMapping("api/groups")
@Tag(name = "api.group.tag.name", description = "api.group.tag.description")
public class GroupController {
    private final GroupsService groupsService;

    @Autowired
    public GroupController(GroupsService groupsService) {
        this.groupsService = groupsService;
    }

    @Operation(summary = "api.group.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.group.api-responses.200.description"),
            @ApiResponse(responseCode = "500", description = "api.server.error", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))})
            // 404
    })
    @GetMapping("{username}")
    public ResponseEntity<GroupDto> getChats(@PathVariable @Size(min = 5, max = 32) String username){
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "api.group.messages.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.group.messages.api-responses.200.description"),
            @ApiResponse(responseCode = "500", description = "api.server.error", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))})
            // 404
    })
    @GetMapping()
    public ResponseEntity<List<MessageDto>> getMessages(@RequestBody @Valid GroupDto group){
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "api.group.messages.send.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.group.messages.send.api-responses.200.description"),
            @ApiResponse(responseCode = "500", description = "api.server.error", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))})
            // 404
    })
    @PostMapping("{groupId}")
    public ResponseEntity<Void> sendMessage(){
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "api.group.create.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.group.create.api-responses.200.description"),
            @ApiResponse(responseCode = "500", description = "api.server.error", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))})
            // 404
    })
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody @Valid GroupDto group){
        return ResponseEntity.ok().build();
    }
}
