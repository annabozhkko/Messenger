package ru.cft.shift.intensive.template.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.shift.intensive.template.dto.UserDto;
import ru.cft.shift.intensive.template.dto.UsernameDto;
import ru.cft.shift.intensive.template.exception.UsernameNotFoundException;
import ru.cft.shift.intensive.template.repository.UsersRepository;
import ru.cft.shift.intensive.template.repository.entity.Users;
import ru.cft.shift.intensive.template.service.UsersService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UsersServiceImpl implements UsersService {
  private final UsersRepository usersRepository;

  private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

  @Autowired
  public UsersServiceImpl(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  @Override
  public List<UsernameDto> list() {
    return this.usersRepository.findAll().stream().map(users -> new UsernameDto(users.getUsername())).toList();
  }

  @Override
  public UserDto findByUsername(String username) {
    return this.usersRepository.findById(username)
        .map(user -> new UserDto(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(),
                user.getPatronymic(), user.getBirthday()))
        .orElseThrow(UsernameNotFoundException::new);
  }

  @Override
  public UsernameDto create(UserDto user) {
    Users users = new Users(user.username(), user.password(), user.firstName(), user.lastName(),
            user.patronymic(), user.birthday());
    logger.info("User " + user.username() + " created");
    return new UsernameDto(this.usersRepository.save(users).getUsername());
  }

  @Override
  public void delete(String username) {
    Users users = this.usersRepository.findById(username).orElseThrow(UsernameNotFoundException::new);
    logger.info("User " + username + " deleted");
    this.usersRepository.delete(users);
  }

  @Override
  public UsernameDto update(UserDto user) {
    Users oldUsers = usersRepository.findById(user.username()).orElseThrow(UsernameNotFoundException::new);
    String password = oldUsers.getPassword();
    Users users = new Users(user.username(), password, user.firstName(), user.lastName(),
            user.patronymic(), user.birthday());
    logger.info("User " + user.username() + " updated");
    return new UsernameDto(this.usersRepository.save(users).getUsername());
  }
}
