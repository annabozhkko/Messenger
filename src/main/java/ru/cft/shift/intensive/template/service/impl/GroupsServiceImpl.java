package ru.cft.shift.intensive.template.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.shift.intensive.template.dto.GroupDto;
import ru.cft.shift.intensive.template.dto.GroupIdDto;
import ru.cft.shift.intensive.template.dto.GroupMessageDto;
import ru.cft.shift.intensive.template.repository.GroupMessagesRepository;
import ru.cft.shift.intensive.template.repository.GroupsRepository;
import ru.cft.shift.intensive.template.repository.entity.Groups;
import ru.cft.shift.intensive.template.service.GroupsService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static ru.cft.shift.intensive.template.utils.MessengerUtils.*;

@Service
public class GroupsServiceImpl implements GroupsService {
    private final GroupsRepository groupsRepository;
    private final GroupMessagesRepository groupMessagesRepository;

    private static final Logger logger = LoggerFactory.getLogger(GroupsServiceImpl.class);

    @Autowired
    public GroupsServiceImpl(GroupsRepository groupsRepository, GroupMessagesRepository groupMessagesRepository) {
        this.groupsRepository = groupsRepository;
        this.groupMessagesRepository = groupMessagesRepository;
    }

    @Override
    public List<GroupMessageDto> getMessages(UUID groupId, LocalDate date) {
        return groupMessagesRepository.findByKey_GroupIdAndKey_Date(groupId, date).stream()
                .map(TO_GROUP_MESSAGES_DTO_FUNCTION).toList();
    }

    @Override
    public GroupIdDto create(GroupDto groupDto) {
        UUID groupId = UUID.randomUUID();

        Groups groups = new Groups();
        groups.setId(groupId);
        groups.setTitle(groupDto.title());
        groups.setAdmin(groupDto.admin());
        groups.setUsers(groupDto.users());

        groupsRepository.save(groups);

        logger.info("Group chat " + groupId + " created");

        return new GroupIdDto(groupId);
    }

    @Override
    public List<GroupDto> getChats(String username) {
        return groupsRepository.findByUsersContains(username).stream().map(TO_GROUPS_DTO_FUNCTION).toList();
    }
}
