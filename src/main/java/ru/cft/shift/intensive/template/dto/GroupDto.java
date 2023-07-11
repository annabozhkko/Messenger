package ru.cft.shift.intensive.template.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.UUID;

public record GroupDto(@NotEmpty UUID groupId,
                       @NotEmpty @Size(min = 5, max = 32) String admin,
                       @NotEmpty @Size(min = 1, max = 255) String title,
                       @NotEmpty Set<UserDto> users) {
}
