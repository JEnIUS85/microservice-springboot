package com.tele.microsrv.platform.services;

import com.tele.microsrv.platform.model.Dummy_User;

public interface UserService {
    void save(Dummy_User user);

    Dummy_User findByUsername(String username);
}