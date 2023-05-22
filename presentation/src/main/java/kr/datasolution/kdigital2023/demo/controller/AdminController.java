package kr.datasolution.kdigital2023.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.datasolution.kdigital2023.demo.model.Department;
import kr.datasolution.kdigital2023.demo.model.Hobby;
import kr.datasolution.kdigital2023.demo.model.User;
import kr.datasolution.kdigital2023.demo.model.UserStatus;
import kr.datasolution.kdigital2023.demo.service.DepartmentService;
import kr.datasolution.kdigital2023.demo.service.HobbyService;
import kr.datasolution.kdigital2023.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private HobbyService hobbyService;
    @Autowired
    private DepartmentService departmentService;

    // 사용자 관리 /////////////////////////////////////////////////////////////////
    @GetMapping("")
    public String getIndex(Model model, User userInfo) {
        log.debug("accessed admin index page");

        List<User> users = userService.getAllUsers();
        List<Hobby> hobbies = hobbyService.getHobbies();
        List<Department> departments = departmentService.getAllDepartment();
        List<UserStatus> userStatus = userService.getAllUserStatus();

		model.addAttribute("userList", users);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("allHobbies", hobbies);
		model.addAttribute("allDepartment", departments);
		model.addAttribute("allUserStatus", userStatus);

        return "/admin/index";
    }

    @GetMapping("/user/{userId}")
    public User getUserInfo(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/user/{userId}")
    public String updateUser(Model model, @PathVariable("userId") String userId, @ModelAttribute User userInfo) {
        User user = userService.updateUser(userId, userInfo);

        List<User> users = userService.getAllUsers();
        List<Hobby> hobbies = hobbyService.getHobbies();
        List<Department> departments = departmentService.getAllDepartment();
        List<UserStatus> userStatus = userService.getAllUserStatus();

		model.addAttribute("userList", users);
		model.addAttribute("userInfo", user);
		model.addAttribute("allHobbies", hobbies);
		model.addAttribute("allDepartment", departments);
		model.addAttribute("allUserStatus", userStatus);

        return "/admin/index";
    }

    @DeleteMapping("/user/{userId}")
    public String deleteUser(Model model, @PathVariable("userId") String userId) {
        userService.deleteUser(userId);

        List<User> users = userService.getAllUsers();
        List<Hobby> hobbies = hobbyService.getHobbies();
        List<Department> departments = departmentService.getAllDepartment();
        List<UserStatus> userStatus = userService.getAllUserStatus();

		model.addAttribute("userList", users);
		model.addAttribute("userInfo", new User());
		model.addAttribute("allHobbies", hobbies);
		model.addAttribute("allDepartment", departments);
		model.addAttribute("allUserStatus", userStatus);

        return "/admin/index";
    }
    ///////////////////////////////////////////////////////////////// 사용자 관리 //


    // 취미 관리 //////////////////////////////////////////////////////////////////
    @GetMapping("/hobby")
    public String getHobbyMgmt(Model model) {
        List<Hobby> hobbies = hobbyService.getHobbies();

        model.addAttribute("allHobbies", hobbies);
        model.addAttribute("hobby", new Hobby());

        return "/admin/hobbyMgmt";
    }

    @PostMapping("/hobby")
    public String addHobby(Model model, @ModelAttribute Hobby hobby) {
        hobbyService.addHobby(hobby);

        List<Hobby> hobbies = hobbyService.getHobbies();

        model.addAttribute("allHobbies", hobbies);
        model.addAttribute("hobby", new Hobby());

        return "/admin/hobbyMgmt";

    }

    @DeleteMapping("/hobby/{hobbyId}")
    public String deleteHobby(Model model, @PathVariable("hobbyId") Integer hobbyId) {
        hobbyService.deleteHobby(hobbyId);

        List<Hobby> hobbies = hobbyService.getHobbies();

        model.addAttribute("allHobbies", hobbies);
        model.addAttribute("hobby", new Hobby());

        return "/admin/hobbyMgmt";

    }
    ////////////////////////////////////////////////////////////////// 취미 관리 //


    // 부서 관리 //////////////////////////////////////////////////////////////////

	@GetMapping("/department")
	public String getDepartmentMgmt(Model model) {
		List<Department> departments = departmentService.getAllDepartment();

		model.addAttribute("allDepartment", departments);
		model.addAttribute("department", new Department());

		return "/admin/departmentMgmt";
	}

	@PostMapping("/department")
	public String addDepartment(Model model, @ModelAttribute Department department) {
		departmentService.addDepartment(department);

		List<Department> departments = departmentService.getAllDepartment();

		model.addAttribute("allDepartment", departments);
		model.addAttribute("department", new Department());
		return "/admin/departmentMgmt";
	}

	@DeleteMapping("/department/{departmentId}")
	public String deleteDepartment(Model model, @PathVariable Integer departmentId) {
		departmentService.delDepartment(departmentId);

		List<Department> departments = departmentService.getAllDepartment();

		model.addAttribute("allDepartment", departments);
		model.addAttribute("department", new Department());

		return "/admin/departmentMgmt";
	}
    ////////////////////////////////////////////////////////////////// 부서 관리 //
}
