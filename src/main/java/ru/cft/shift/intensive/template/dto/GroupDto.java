package ru.cft.shift.intensive.template.dto;

import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.UUID;

public record GroupDto(UUID groupId,
                       @Size(min = 5, max = 32) String admin,
                       @Size(min = 1, max = 255) String title,
                       Set<String> users) {
}
