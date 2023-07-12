package ru.cft.shift.intensive.template.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record GroupMessageDto(UUID groupId,
                              @NotEmpty @Size(min = 5, max = 32) String userFrom,
                              @NotEmpty @Size(min = 1, max = 255) String message,
                              @NotNull LocalDate date,
                              @NotNull LocalTime time){
}
