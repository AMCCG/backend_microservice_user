package com.backend.user.services;

import com.backend.user.entity.UserEntity;
import com.backend.user.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(UserEntity user) {
        return this.userRepository.save(user);
    }

    public List<UserEntity> getAllUser() {
        return this.userRepository.findAll();
    }

    public UserEntity getUserById(String id) throws Exception {
        Optional<UserEntity> opt = this.userRepository.findById(id);
        if (!opt.isPresent()) {
            throw new Exception();
        }
        return opt.get();
    }
}
