package org.example.user_module.controller;

import org.example.user_module.entity.User;
import org.example.user_module.entity.UserQueryParams;
import org.example.user_module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping
////    public List<User> getUsers() {
////        return userService.getAllUsers();
////    }

    @GetMapping
    public Map<String, Object> getUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String search) {

        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<User> userPage = userService.getUsers(search, pageable);

        // 返回分页结果
        Map<String, Object> response = new HashMap<>();
        response.put("items", userPage.getContent());
        response.put("total", userPage.getTotalElements());
        return response;
    }

//    @GetMapping()
//    public List<User> queryUsers(@RequestBody UserQueryParams params) {
//        System.out.println(params);
////        Logger logger = Logger.getLogger(params.getClass().getName());
////        logger.info("query users: " + params);
//        return userService.getUsersByName(params);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return ResponseEntity.ok(userService.updateUser(id, userDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
