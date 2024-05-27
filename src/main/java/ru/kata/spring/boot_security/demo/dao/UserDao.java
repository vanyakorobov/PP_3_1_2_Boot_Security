package ru.kata.spring.boot_security.demo.dao;



import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {

    User getUserById(Long id);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    List<User> getAllUsers();

}
