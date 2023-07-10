package ru.cft.shift.intensive.template.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import ru.cft.shift.intensive.template.repository.entity.Messages;

@Repository
public interface MessagesRepository extends CassandraRepository<Messages, String> {
}
