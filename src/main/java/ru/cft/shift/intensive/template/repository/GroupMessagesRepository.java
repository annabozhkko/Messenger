package ru.cft.shift.intensive.template.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import ru.cft.shift.intensive.template.repository.entity.GroupMessages;

@Repository
public interface GroupMessagesRepository extends CassandraRepository<GroupMessages, String> {
}
