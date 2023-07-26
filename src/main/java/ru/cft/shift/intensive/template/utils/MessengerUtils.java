package ru.cft.shift.intensive.template.utils;

import ru.cft.shift.intensive.template.dto.GroupDto;
import ru.cft.shift.intensive.template.dto.GroupMessageDto;
import ru.cft.shift.intensive.template.dto.MessageDto;
import ru.cft.shift.intensive.template.repository.entity.GroupMessages;
import ru.cft.shift.intensive.template.repository.entity.Groups;
import ru.cft.shift.intensive.template.repository.entity.Messages;

import java.util.function.Function;

public class MessengerUtils {
    public static final Function<Messages, MessageDto> TO_MESSAGES_DTO_FUNCTION = messages ->
            new MessageDto(messages.getUserFrom(), messages.getUserTo(), messages.getMessage(), messages.getDate(), messages.getTime());

    public static final Function<GroupMessages, GroupMessageDto> TO_GROUP_MESSAGES_DTO_FUNCTION = groupMessages ->
            new GroupMessageDto(groupMessages.getGroupId(), groupMessages.getUserFrom(), groupMessages.getMessage(),
                    groupMessages.getDate(), groupMessages.getTime());

    public static final Function<Groups, GroupDto> TO_GROUPS_DTO_FUNCTION = groups ->
            new GroupDto(groups.getAdmin(), groups.getTitle(), groups.getUsers());

}
