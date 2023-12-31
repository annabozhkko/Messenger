package ru.cft.shift.intensive.template.repository;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import ru.cft.shift.intensive.template.repository.entity.Groups;

import java.util.List;

@Repository
public interface GroupsRepository extends CassandraRepository<Groups, String> {
    @AllowFiltering
    List<Groups> findByUsersContains(String user);
}
