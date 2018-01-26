package com.asiflogin.loginmvvm0126.trade.repositories;

import com.asiflogin.loginmvvm0126.trade.entities.User;

public interface UserRepository {
    void save(User user) throws UserAlreadyExistsException;

    User fetchByEmail(String email);

}
