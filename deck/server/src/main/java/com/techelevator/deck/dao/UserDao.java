package com.techelevator.deck.dao;

import com.techelevator.deck.model.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> findByUsername(String username);

}
