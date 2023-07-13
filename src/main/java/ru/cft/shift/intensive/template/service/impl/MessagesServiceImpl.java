package ru.cft.shift.intensive.template.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.shift.intensive.template.dto.MessageDto;
import ru.cft.shift.intensive.template.dto.UsernameDto;
import ru.cft.shift.intensive.template.repository.MessagesRepository;
import ru.cft.shift.intensive.template.repository.entity.Messages;
import ru.cft.shift.intensive.template.service.MessagesService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static ru.cft.shift.intensive.template.utils.MessengerUtils.*;

@Service
public class MessagesServiceImpl implements MessagesService {
    private final MessagesRepository messagesRepository;

    private static final Logger logger = LoggerFactory.getLogger(MessagesServiceImpl.class);

    @Autowired
    public MessagesServiceImpl(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @Override
    public List<MessageDto> getMessages(String user1, String user2, LocalDate date) {
        List<Messages> messagesFromUser1 = messagesRepository.findByKey_UserFromAndKey_UserToAndKey_Date(user1, user2, date);
        List<Messages> messagesFromUser2 = messagesRepository.findByKey_UserFromAndKey_UserToAndKey_Date(user2, user1, date);

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

        logger.info("Message from " + messageDto.userFrom() + " to " + messageDto.userTo() + " sent");
    }

    @Override
    public List<UsernameDto> getChats(String username) {
        List<Messages> messagesTo = messagesRepository.findByKey_UserTo(username);
        List<Messages> messagesFrom = messagesRepository.findByKey_UserFrom(username);

        List<UsernameDto> usersTo = messagesTo.stream().map(messages ->
                new UsernameDto(messages.getUserFrom())).distinct().toList();
        List<UsernameDto> usersFrom = messagesFrom.stream().map(messages ->
                new UsernameDto(messages.getUserTo())).distinct().toList();

        return Stream.concat(usersTo.stream(), usersFrom.stream()).distinct().toList();
    }
}
