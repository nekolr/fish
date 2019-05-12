package com.nekolr.fish.service.impl;

import com.nekolr.fish.dao.UserRepository;
import com.nekolr.fish.entity.User;
import com.nekolr.fish.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author nekolr
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
