package ru.cft.shift.intensive.template.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.shift.intensive.template.dto.GroupDto;
import ru.cft.shift.intensive.template.dto.GroupMessageDto;
import ru.cft.shift.intensive.template.repository.GroupMessagesRepository;
import ru.cft.shift.intensive.template.repository.GroupsRepository;
import ru.cft.shift.intensive.template.repository.entity.Groups;
import ru.cft.shift.intensive.template.service.GroupsService;

import java.util.List;
import java.util.UUID;

import static ru.cft.shift.intensive.template.utils.MessengerUtils.*;

@Service
public class GroupsServiceImpl implements GroupsService {
    private final GroupsRepository groupsRepository;
    private final GroupMessagesRepository groupMessagesRepository;

    @Autowired
    public GroupsServiceImpl(GroupsRepository groupsRepository, GroupMessagesRepository groupMessagesRepository) {
        this.groupsRepository = groupsRepository;
        this.groupMessagesRepository = groupMessagesRepository;
    }

    @Override
    public List<GroupMessageDto> getMessages(UUID groupId) {
        // проверка что есть
        return groupMessagesRepository.findByKey_GroupId(groupId).stream()
                .map(TO_GROUP_MESSAGES_DTO_FUNCTION).toList();
    }

    @Override
    public void create(GroupDto groupDto) {
        Groups groups = new Groups();
        groups.setId(UUID.randomUUID());
        groups.setTitle(groupDto.title());
        groups.setAdmin(groupDto.admin());
        groups.setUsers(groupDto.users());

        groupsRepository.save(groups);
    }

    @Override
    public List<GroupDto> getChats(String username) {
        return groupsRepository.findByUsersContains(username).stream().map(TO_GROUPS_DTO_FUNCTION).toList();
    }
}
