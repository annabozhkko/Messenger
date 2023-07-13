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
import ru.cft.shift.intensive.template.dto.GroupDto;
import ru.cft.shift.intensive.template.dto.GroupIdDto;
import ru.cft.shift.intensive.template.dto.GroupMessageDto;
import ru.cft.shift.intensive.template.service.GroupMessagesService;
import ru.cft.shift.intensive.template.service.GroupsService;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@RestController
@RequestMapping("api/groups")
@Tag(name = "api.group.tag.name", description = "api.group.tag.description")
public class GroupController {
    private final GroupsService groupsService;
    private final GroupMessagesService groupMessagesService;

    @Autowired
    public GroupController(GroupsService groupsService, GroupMessagesService groupMessagesService) {
        this.groupsService = groupsService;
        this.groupMessagesService = groupMessagesService;
    }

    @Operation(summary = "api.group.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.group.api-responses.200.description"),
            @ApiResponse(responseCode = "500", description = "api.server.error", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))})
    })
    @GetMapping("{username}")
    public ResponseEntity<List<GroupDto>> getChats(@PathVariable @Size(min = 5, max = 32) String username){
        return ResponseEntity.ok(groupsService.getChats(username));
    }

    @Operation(summary = "api.group.messages.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.group.messages.api-responses.200.description"),
            @ApiResponse(responseCode = "500", description = "api.server.error", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))})
            // 404
    })
    @GetMapping()
    public ResponseEntity<List<GroupMessageDto>> getMessages(@RequestBody @Valid GroupIdDto groupId, @RequestParam("date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return ResponseEntity.ok(groupsService.getMessages(groupId.groupId(), date));
    }

    @Operation(summary = "api.group.messages.send.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.group.messages.send.api-responses.200.description"),
            @ApiResponse(responseCode = "500", description = "api.server.error", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))})
    })
    @PostMapping()
    public ResponseEntity<Void> sendMessage( @RequestBody @Valid GroupMessageDto message){
        groupMessagesService.sendMessage(message);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "api.group.create.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.group.create.api-responses.200.description"),
            @ApiResponse(responseCode = "500", description = "api.server.error", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))})
    })
    @PostMapping("/new")
    public ResponseEntity<GroupIdDto> create(@RequestBody @Valid GroupDto group){
        return ResponseEntity.ok(groupsService.create(group));
    }
}
