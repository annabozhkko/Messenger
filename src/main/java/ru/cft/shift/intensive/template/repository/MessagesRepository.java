package ru.cft.shift.intensive.template.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import ru.cft.shift.intensive.template.repository.entity.Messages;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MessagesRepository extends CassandraRepository<Messages, String> {
    List<Messages> findByKey_UserFromAndKey_UserToAndKey_Date(String userFrom, String userTo, LocalDate date);
}
