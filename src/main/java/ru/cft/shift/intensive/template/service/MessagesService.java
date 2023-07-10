package ru.cft.shift.intensive.template.service;

import ru.cft.shift.intensive.template.dto.MessageDto;

import java.util.List;

public interface MessagesService {
    List<MessageDto> getMessages(String username1, String username2);

    void sendMessage(MessageDto message);
}
