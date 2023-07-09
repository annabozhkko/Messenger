package ru.cft.shift.intensive.template.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;
import java.util.Date;

public record MessageDto(@NotEmpty @Size(min = 5, max = 32) String userFrom,
                         @NotEmpty @Size(min = 5, max = 32) String userTo,
                         @NotEmpty @Size(min = 1, max = 255) String message,
                         @NotEmpty Date date,
                         @NotEmpty LocalTime time){
}
