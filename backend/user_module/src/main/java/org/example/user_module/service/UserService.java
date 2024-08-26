package org.example.user_module.service;

import org.example.user_module.entity.User;
import org.example.user_module.entity.UserQueryParams;
import org.example.user_module.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Page<User> getUsers(String search, Pageable pageable) {
        if (search != null && !search.isEmpty()) {
            // 按用户名模糊搜索
            return userRepository.findByUserNameContainingIgnoreCase(search, pageable);
        } else {
            // 返回所有用户，分页
            return userRepository.findAll(pageable);
        }
    }

//    public List<User> getUsersByName(UserQueryParams params) {
//        return userRepository.findAllByPropertyName(params.getSearch());
//           }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUserName(userDetails.getUserName());
        user.setTrueName(userDetails.getTrueName());
        user.setPassword(userDetails.getPassword());
        user.setEmail(userDetails.getEmail());
        user.setGender(userDetails.getGender());
        user.setRoleIds(userDetails.getRoleIds());
        user.setAvatarUrl(userDetails.getAvatarUrl());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}
