package ru.cft.shift.intensive.template.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserDto(@NotEmpty @Size(min = 5, max = 32) String username,
                      @Size(min = 8, max = 16) String password,
                      @NotEmpty @Size(min = 1, max = 64) String firstName,
                      @NotEmpty @Size(min = 1, max = 64) String lastName,
                      @Size(min = 1, max = 64) String patronymic,
                      LocalDate birthday) {
}
