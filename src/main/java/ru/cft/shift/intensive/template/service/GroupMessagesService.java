package ru.cft.shift.intensive.template.service;

import ru.cft.shift.intensive.template.dto.GroupMessageDto;

import java.util.UUID;

public interface GroupMessagesService {
    void sendMessage(GroupMessageDto message);
}
