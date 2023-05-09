package com.wjf.user.service;

import com.wjf.user.entity.User;

import java.util.List;

/**
 * @author JunFei
 * @data
 * @purpose
 */

public interface UserService {

    String CreateUser(User user);

    Boolean login(User user);

    User getUser(String loginName);
}
