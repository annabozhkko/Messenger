package ru.cft.shift.intensive.template.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record ChatDto(@NotEmpty(message = "{validation.chat.users.not-empty}") @Size(min = 5, max = 32) String user1,
                      @NotEmpty(message = "{validation.chat.users.not-empty}") @Size(min = 5, max = 32) String user2){
}
