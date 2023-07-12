package ru.cft.shift.intensive.template.service;

import ru.cft.shift.intensive.template.dto.GroupDto;
import ru.cft.shift.intensive.template.dto.GroupMessageDto;

import java.util.List;
import java.util.UUID;

public interface GroupsService {
    List<GroupMessageDto> getMessages(UUID groupId);

    void create(GroupDto groupDto);

    List<GroupDto> getChats(String username);
}
