package kr.datasolution.kdigital2023.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.datasolution.kdigital2023.demo.mapper.UserHobbiesMapper;
import kr.datasolution.kdigital2023.demo.mapper.UserStatusMapper;
import kr.datasolution.kdigital2023.demo.mapper.UsersMapper;
import kr.datasolution.kdigital2023.demo.model.Department;
import kr.datasolution.kdigital2023.demo.model.User;
import kr.datasolution.kdigital2023.demo.model.UserStatus;

@Service
public class UserMgmtService {
    private static final Logger log = LoggerFactory.getLogger(UserMgmtService.class);
    
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UserHobbiesMapper userHobbiesMapper;
    @Autowired
	private UserStatusMapper userStatusMapper;
    
    @Autowired
	private DepartmentMgmtService departmentMgmtService;
    
    public User enrollUser(User enrollInfo) {
        enrollInfo.setStatusId(1);
        enrollInfo.setDepartmentId(9999);

        UserStatus userStatus = userStatusMapper.findById(enrollInfo.getStatusId());
        enrollInfo.setStatus(userStatus.getStatus());

        Department department = departmentMgmtService.getDepartment(enrollInfo.getDepartmentId());
        enrollInfo.setDepartment(department.getDepartment());

        usersMapper.save(enrollInfo);

        for (Integer hobbyId : enrollInfo.getHobbies()) {
            userHobbiesMapper.saveUserHobboy(enrollInfo.getUserId(), hobbyId);
        }

        log.debug("enrollment saved : {}" + enrollInfo.getUserId());

        return enrollInfo;
    }

    private User fillUser(User user) {
        UserStatus userStatus = userStatusMapper.findById(user.getStatusId());
        user.setStatus(userStatus.getStatus());

        List<Integer> hobbies = userHobbiesMapper.findHobbyIdByUserId(user.getUserId());
        user.setHobbies(hobbies);

        Department department = departmentMgmtService.getDepartment(user.getDepartmentId());
        user.setDepartment(department.getDepartment());

        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = usersMapper.findAll();
        
        for (User user: users) {
            user = this.fillUser(user);
        }

        return users;
    }

    public User getUser(String userId) {
        User user = usersMapper.find(userId);

        return fillUser(user);
    }

    public User upateUser(String userId, User userInfo) {
        if (userId.equals(userInfo.getUserId())) {
            usersMapper.update(userInfo);
        }

        return getUser(userId);
    }

    public Integer deleteUser(String userId) {
        return usersMapper.remove(userId);
    }

    public List<UserStatus> getAllUserStatus() {
        return userStatusMapper.findAll();
    }
}
