package ru.cft.shift.intensive.template.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record GroupDto(@NotEmpty(message = "{validation.group.admin.not-empty}") @Size(min = 5, max = 32) String admin,
                       @NotEmpty(message = "{validation.group.title.not-empty}") @Size(min = 1, max = 255) String title,
                       @NotEmpty(message = "{validation.group.users.not-empty}") Set<String> users) {
}
