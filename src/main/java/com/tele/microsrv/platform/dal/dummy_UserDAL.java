package com.tele.microsrv.platform.dal;

import com.tele.microsrv.platform.model.Dummy_User;

import java.util.List;

public interface dummy_UserDAL {

    List<Dummy_User> getAllUsers();

    Dummy_User getUserById(String userId);

    Dummy_User addNewUser(Dummy_User user);

    /*Object getAllUserSettings(String userId);

    String getUserSetting(String userId, String key);

    String addUserSetting(String userId, String key, String value);*/

    Dummy_User getEmail(String email);

}
