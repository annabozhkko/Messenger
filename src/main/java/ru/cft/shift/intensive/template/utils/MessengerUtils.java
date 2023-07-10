package ru.cft.shift.intensive.template.utils;

import ru.cft.shift.intensive.template.dto.MessageDto;
import ru.cft.shift.intensive.template.repository.entity.Messages;

import java.util.function.Function;

public class MessengerUtils {
    public static final Function<Messages, MessageDto> TO_MESSAGES_DTO_FUNCTION = messages ->
            new MessageDto(messages.getUserFrom(), messages.getUserTo(), messages.getMessage(), messages.getDate(), messages.getTime());
}
