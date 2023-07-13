package ru.cft.shift.intensive.template.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record GroupMessageDto(@NotNull(message = "{validation.group.messages.group-id.not-empty}") UUID groupId,
                              @NotEmpty(message = "{validation.group.messages.user-from.not-empty}") @Size(min = 5, max = 32) String userFrom,
                              @NotEmpty(message = "{validation.group.messages.message.not-empty}") @Size(min = 1, max = 255) String message,
                              @NotNull(message = "{validation.group.messages.date.not-empty}") LocalDate date,
                              @NotNull(message = "{validation.group.messages.time.not-empty}") LocalTime time){
}
