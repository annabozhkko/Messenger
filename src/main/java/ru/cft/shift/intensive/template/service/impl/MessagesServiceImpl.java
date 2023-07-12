package ru.cft.shift.intensive.template.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.shift.intensive.template.dto.MessageDto;
import ru.cft.shift.intensive.template.repository.MessagesRepository;
import ru.cft.shift.intensive.template.repository.entity.Messages;
import ru.cft.shift.intensive.template.service.MessagesService;

import java.util.*;

import static ru.cft.shift.intensive.template.utils.MessengerUtils.*;

@Service
public class MessagesServiceImpl implements MessagesService {
    private final MessagesRepository messagesRepository;

    @Autowired
    public MessagesServiceImpl(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @Override
    public List<MessageDto> getMessages(String user1, String user2) {
        List<Messages> messagesFromUser1 = messagesRepository.findByKey_UserFromAndKey_UserTo(user1, user2);
        List<Messages> messagesFromUser2 = messagesRepository.findByKey_UserFromAndKey_UserTo(user2, user1);

        List<Messages> allMessages = new ArrayList<>();
        allMessages.addAll(messagesFromUser1);
        allMessages.addAll(messagesFromUser2);

        // сортировка по дате и времени
        allMessages.sort((m1, m2) -> {
            int dateComparison = m1.getDate().compareTo(m2.getDate());
            if (dateComparison != 0) {
                return dateComparison;
            } else {
                return m1.getTime().compareTo(m2.getTime());
            }
        });

        return allMessages.stream().map(TO_MESSAGES_DTO_FUNCTION).toList();
    }

    @Override
    public void sendMessage(MessageDto messageDto) {
        Messages messages = new Messages();
        messages.setUserFrom(messageDto.userFrom());
        messages.setUserTo(messageDto.userTo());
        messages.setMessage(messageDto.message());
        messages.setDate(messageDto.date());
        messages.setTime(messageDto.time());

        messagesRepository.save(messages);
    }
}
