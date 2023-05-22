package kr.datasolution.kdigital2023.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.datasolution.kdigital2023.demo.model.User;
import kr.datasolution.kdigital2023.demo.model.UserStatus;
import kr.datasolution.kdigital2023.demo.service.UserMgmtService;


@RestController
@RequestMapping("/api")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserMgmtService userMgmtService;


    @PostMapping("/user")
    public ResponseEntity<User> enrollUser(@RequestBody User enrollInfo) {
        try {
            User rsponseBody = userMgmtService.enrollUser(enrollInfo);
            ResponseEntity<User> resEntity = new ResponseEntity<User>(rsponseBody, HttpStatus.CREATED);

            return resEntity;
        
        } catch (RuntimeException e) {
            log.error("RuntimeException message: {}", e.getMessage());
			
			if(e.getMessage().contains("users_pkey")) {
				return new ResponseEntity<User>(HttpStatus.CONFLICT);
			} else {
				throw e;
			}
        }
    }

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userMgmtService.getAllUsers();
    }

    @GetMapping("/user/{userId}")
    public User getUser(@PathVariable("userId") String userId) {
        return userMgmtService.getUser(userId);
    }

    @PutMapping("/user/{userId}")
    public User updateUser(@PathVariable("userId") String userId, @RequestBody User userInfo) {
        return userMgmtService.upateUser(userId, userInfo);
    }

    @DeleteMapping("/user/{userId}")
    public Integer deleteUser(@PathVariable("userId") String userId) {
        return userMgmtService.deleteUser(userId);
    }

    @GetMapping("/user-status")
    public List<UserStatus> getAllUserStatus() {
        return userMgmtService.getAllUserStatus();
    }
    
}
