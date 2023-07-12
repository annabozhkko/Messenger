package ru.cft.shift.intensive.template.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record GroupIdDto(@NotNull UUID groupId) {
}
