package com.wjf.user.service;

import com.wjf.user.entity.User;
import com.wjf.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author JunFei
 * @data
 * @purpose
 */

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;


    @Override
    public String CreateUser(User user) {
        int userUid = (int) (Math.random() * 100);
        user.setUserUid(userUid);
        String flag;
        User user1 = userRepository.save(user);
        if (user1.getId() > 0) {
            flag = "success";
        } else {
            flag = "error";
        }
        return flag;
    }

    //判断是否存在该用户,用户名和密码是否正确且匹配
    @Override
    public Boolean login(User user) {
        String name = user.getName();
        String password = user.getPassword();
        List<User> users = userRepository.findAll();
        for (User u:users){
            if (name.equals(u.getName()) && password.equals(u.getPassword())){
                return true;
            }
        }
        return false;
    }

    @Override
    public User getUser(String name) {
        return userRepository.findByUserName(name)
                .orElseThrow(()-> new EntityNotFoundException(String.format("User not found for name %s", name)));

    }


}
