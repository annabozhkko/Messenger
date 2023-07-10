package ru.cft.shift.intensive.template.repository.entity;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;

@Table("users")
public class Users {
  @PrimaryKey(value = "username")
  private String username;
  @Column(value = "first_name")
  private String firstName;
  @Column(value = "last_name")
  private String lastName;
  @Column(value = "patronymic")
  private String patronymic;
  @Column(value = "password")
  private String password;
  @Column(value = "birthday")
  private LocalDate birthday;

  public Users() {
  }

  public Users(String username, String password, String firstName, String lastName, String patronymic, LocalDate birthday) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.patronymic = patronymic;
    this.birthday = birthday;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getPatronymic() {
    return patronymic;
  }

  public void setPatronymic(String patronymic) {
    this.patronymic = patronymic;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }
}
