package com.microservice.backend.user.services;

import com.microservice.backend.user.entity.UserEntity;
import com.microservice.backend.user.exception.BaseException;
import com.microservice.backend.user.exception.UserException;
import com.microservice.backend.user.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(String email, String password, String userName) throws BaseException {
        Optional<UserEntity> opt = this.userRepository.findByEmail(email);
        if (opt.isPresent()) {
            throw UserException.emailExisting();
        }
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setPassword(password);
        user.setUserName(userName);
        return this.userRepository.save(user);
    }

    public List<UserEntity> getAllUser() {
        return this.userRepository.findAll();
    }

    public UserEntity getUserById(String id) throws BaseException {
        Optional<UserEntity> opt = this.userRepository.findById(id);
        if (!opt.isPresent()) {
            throw UserException.userNotFound();
        }
        return opt.get();
    }

    public void deleteUserByEmail(String email) {
        this.userRepository.deleteByEmail(email);
    }

    public UserEntity getUserByEmail(String email) throws BaseException {
        Optional<UserEntity> opt = this.userRepository.findByEmail(email);
        if (!opt.isPresent()) {
            throw UserException.userNotFound();
        }
        return opt.get();
    }
}
