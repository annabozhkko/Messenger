package ru.cft.shift.intensive.template.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import ru.cft.shift.intensive.template.repository.entity.GroupMessages;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupMessagesRepository extends CassandraRepository<GroupMessages, String> {
    List<GroupMessages> findByKey_GroupId(UUID groupId);
}
