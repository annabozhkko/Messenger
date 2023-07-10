package ru.cft.shift.intensive.template.repository.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@Table("group_messages")
public class GroupMessages {
    @PrimaryKey
    private Key key = new Key();
    @Column
    private String userFrom;
    @Column
    private String message;

    @PrimaryKeyClass
    public static class Key {
        @PrimaryKeyColumn(name = "group_id", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
        private UUID id;
        @PrimaryKeyColumn(name = "message_date", type = PrimaryKeyType.CLUSTERED, ordinal = 1)
        private Date date;
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

    public UUID getId() {
        return key.id;
    }

    public void setId(UUID id) {
        this.key.id = id;
    }

    public void setDate(Date date){
        key.date = date;
    }

    public Date getDate(){
        return key.date;
    }

    public void setTime(LocalTime time){
        key.time = time;
    }

    public LocalTime getTime(){
        return key.time;
    }
}
