package com.example.youtube.Service;

import com.example.youtube.model.User;
import com.example.youtube.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User findById(int id){
        return userRepository.findById(id);
    }
    public void save(User  user){
        userRepository.save(user);
    }
}
