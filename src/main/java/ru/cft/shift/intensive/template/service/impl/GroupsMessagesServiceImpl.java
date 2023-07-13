package ru.cft.shift.intensive.template.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.shift.intensive.template.dto.GroupMessageDto;
import ru.cft.shift.intensive.template.repository.GroupMessagesRepository;
import ru.cft.shift.intensive.template.repository.entity.GroupMessages;
import ru.cft.shift.intensive.template.service.GroupMessagesService;

@Service
public class GroupsMessagesServiceImpl implements GroupMessagesService {
    private final GroupMessagesRepository groupMessagesRepository;

    private static final Logger logger = LoggerFactory.getLogger(GroupsMessagesServiceImpl.class);

    @Autowired
    public GroupsMessagesServiceImpl(GroupMessagesRepository groupMessagesRepository) {
        this.groupMessagesRepository = groupMessagesRepository;
    }

    @Override
    public void sendMessage(GroupMessageDto messageDto) {
        GroupMessages messages = new GroupMessages();
        messages.setGroupId(messageDto.groupId());
        messages.setUserFrom(messageDto.userFrom());
        messages.setMessage(messageDto.message());
        messages.setDate(messageDto.date());
        messages.setTime(messageDto.time());

        groupMessagesRepository.save(messages);

        logger.info("Message from " + messageDto.userFrom() + " to group chat " + messageDto.groupId() + " sent");
    }
}
