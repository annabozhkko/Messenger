package ru.cft.shift.intensive.template.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.shift.intensive.template.dto.GroupMessageDto;
import ru.cft.shift.intensive.template.repository.GroupMessagesRepository;
import ru.cft.shift.intensive.template.repository.entity.GroupMessages;
import ru.cft.shift.intensive.template.service.GroupMessagesService;

@Service
public class GroupsMessagesServiceImpl implements GroupMessagesService {
    private final GroupMessagesRepository groupMessagesRepository;

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
    }
}
