package com.datacoffee.tournament.service.impl;

import com.datacoffee.tournament.entity.User;
import com.datacoffee.tournament.service.IUserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements IUserService {

    @Override
    public Optional<User> findById(Long userId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsernameOrEmail(String email) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void deleteById(Long userId) {

    }
}
