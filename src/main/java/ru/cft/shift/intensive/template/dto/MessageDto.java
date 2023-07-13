package ru.cft.shift.intensive.template.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

public record MessageDto(@NotEmpty(message = "{validation.chat.messages.user-from.not-empty}") @Size(min = 5, max = 32) String userFrom,
                         @NotEmpty(message = "{validation.chat.messages.user-to.not-empty}") @Size(min = 5, max = 32) String userTo,
                         @NotEmpty(message = "{validation.chat.messages.message.not-empty}") @Size(min = 1, max = 255) String message,
                         @NotNull(message = "{validation.chat.messages.date.not-empty}") LocalDate date,
                         @NotNull(message = "{validation.chat.messages.time.not-empty}") LocalTime time){
}
