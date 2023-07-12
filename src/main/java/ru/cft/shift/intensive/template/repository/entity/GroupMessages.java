package ru.cft.shift.intensive.template.repository.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Table("group_messages")
public class GroupMessages {
    @PrimaryKey
    private Key key = new Key();
    @Column(value = "user_from")
    private String userFrom;
    @Column(value = "message")
    private String message;

    @PrimaryKeyClass
    public static class Key {
        @PrimaryKeyColumn(name = "group_id", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
        private UUID groupId;
        @PrimaryKeyColumn(name = "message_date", type = PrimaryKeyType.CLUSTERED, ordinal = 1)
        private LocalDate date;
        @PrimaryKeyColumn(name = "message_time", type = PrimaryKeyType.CLUSTERED, ordinal = 1)
        private LocalTime time;

        public Key() {
        }
    }

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UUID getGroupId() {
        return key.groupId;
    }

    public void setGroupId(UUID groupId) {
        this.key.groupId = groupId;
    }

    public void setDate(LocalDate date){
        key.date = date;
    }

    public LocalDate getDate(){
        return key.date;
    }

    public void setTime(LocalTime time){
        key.time = time;
    }

    public LocalTime getTime(){
        return key.time;
    }
}
