package ru.cft.shift.intensive.template.repository.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.time.LocalTime;
import java.util.Date;

@Table("messages")
public class Messages {
    @PrimaryKey
    private Key key = new Key();
    @Column(value = "message")
    private String message;

    @PrimaryKeyClass
    public static class Key {
        @PrimaryKeyColumn(name = "to_user", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
        private String userTo;
        @PrimaryKeyColumn(name = "from_user", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
        private String userFrom;
        @PrimaryKeyColumn(name = "message_date", type = PrimaryKeyType.CLUSTERED, ordinal = 1)
        private Date date;
        @PrimaryKeyColumn(name = "message_time", type = PrimaryKeyType.CLUSTERED, ordinal = 1)
        private LocalTime time;

        public Key() {
        }

        public Key(String userTo, String userFrom, Date date, LocalTime time) {
            this.userTo = userTo;
            this.userFrom = userFrom;
            this.date = date;
            this.time = time;
        }
    }

    public void setUserTo(String userTo){
        key.userTo = userTo;
    }

    public String getUserTo(){
        return key.userTo;
    }

    public void setUserFrom(String userFrom){
        key.userFrom = userFrom;
    }

    public String getUserFrom(){
        return key.userFrom;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
