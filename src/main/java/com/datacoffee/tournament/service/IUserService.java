package com.datacoffee.tournament.service;

import com.datacoffee.tournament.entity.User;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    public Optional<User> findById(Long userId);
    public Optional<User> findByUsernameOrEmail(String email);

    public List<User> findAll();
    public void save(User user);
    public void update(User user);
    public void deleteById(Long userId);
}
