package ru.cft.shift.intensive.template.service;

import ru.cft.shift.intensive.template.dto.MessageDto;
import ru.cft.shift.intensive.template.dto.UsernameDto;

import java.time.LocalDate;
import java.util.List;

public interface MessagesService {
    List<MessageDto> getMessages(String username1, String username2, LocalDate date);

    void sendMessage(MessageDto message);

    List<UsernameDto> getChats(String username);
}
