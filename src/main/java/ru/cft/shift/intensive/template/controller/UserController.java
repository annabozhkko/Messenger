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
import ru.cft.shift.intensive.template.dto.UserDto;
import ru.cft.shift.intensive.template.dto.UsernameDto;
import ru.cft.shift.intensive.template.service.UsersService;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("api/users")
@Tag(name = "api.user.tag.name", description = "api.user.tag.description")
public class UserController {
  private final UsersService usersService;

  @Autowired
  public UserController(UsersService usersService) {
    this.usersService = usersService;
  }

  @Operation(summary = "api.user.users.operation.summary")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "api.user.users.api-responses.200.description"),
      @ApiResponse(responseCode = "500", description = "api.server.error", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))})
  })
  @GetMapping("list")
  public ResponseEntity<List<UsernameDto>> list() {
    return ResponseEntity.ok(this.usersService.list());
  }

  // из нашего апи Создание пользователя
  @Operation(summary = "api.user.create.operation.summary")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "api.user.create.api-responses.200.description"),
      @ApiResponse(responseCode = "500", description = "api.server.error", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))})
          // HTTP Status 400
  })
  @PostMapping("register")
  public ResponseEntity<UsernameDto> createUser(@RequestBody @Valid UserDto user) {
    return ResponseEntity.ok(this.usersService.create(user));
  }

  @Operation(summary = "api.user.delete.operation.summary")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "api.user.delete.api-responses.200.description"),
      @ApiResponse(responseCode = "404", description = "api.user.delete.api-responses.404.description", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))}),
      @ApiResponse(responseCode = "500", description = "api.server.error", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))})
  })
  @DeleteMapping("{username}")
  public ResponseEntity<Void> deleteUser(@PathVariable @Size(min = 5, max = 32) String username) {
    this.usersService.delete(username);
    return ResponseEntity.ok().build();
  }


  // Получение данных о пользователе
  @Operation(summary = "api.user.get.operation.summary")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "api.user.get.api-responses.200.description"),
      @ApiResponse(responseCode = "404", description = "api.user.get.api-responses.404.description", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))}),
      @ApiResponse(responseCode = "500", description = "api.server.error", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))})
  })
  @GetMapping("{username}")
  public ResponseEntity<UserDto> getUser(@PathVariable @Size(min = 1, max = 64) String username) {
    return ResponseEntity.ok(this.usersService.findByUsername(username));
  }

  // Изменение своего профиля
  @Operation(summary = "api.user.update.operation.summary")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "api.user.update.api-responses.200.description"),
          @ApiResponse(responseCode = "500", description = "api.server.error", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))})
  })
  @PatchMapping()
  public ResponseEntity<UsernameDto> edit(@RequestBody @Valid UserDto user){
    return ResponseEntity.ok(usersService.update(user));
  }

  @GetMapping()
  public ResponseEntity<List<UserDto>> search(@PathVariable @Size(min = 5, max = 32) String username,
                                              @PathVariable @Size(min = 1, max = 64) String firstName,
                                              @PathVariable @Size(min = 1, max = 64) String lastName,
                                              @PathVariable LocalDate birthday){
    return ResponseEntity.ok().build();
  }
}
