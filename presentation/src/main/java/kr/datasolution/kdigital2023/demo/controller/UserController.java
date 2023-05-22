package kr.datasolution.kdigital2023.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.datasolution.kdigital2023.demo.model.Hobby;
import kr.datasolution.kdigital2023.demo.model.User;
import kr.datasolution.kdigital2023.demo.service.HobbyService;
import kr.datasolution.kdigital2023.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    HobbyService hobbyService;
    @Autowired
    UserService userService;

    @GetMapping("")
    public String getEnrollForm(Model model, User userInfo) {
        List<Hobby> hobbies = hobbyService.getHobbies();
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("allHobbies", hobbies);
        return "/user/enrollForm";
    }

    @PostMapping("")
    public String saveEnrollForm(Model model, @ModelAttribute User userInfo) {
        log.debug(userInfo.toString());
        String viewName = "/user/enrollDone";

        try {
            User enrolled = userService.enrollUser(userInfo);

            List<Hobby> hobbies = hobbyService.getHobbies();
			model.addAttribute("allHobbies", hobbies);
			model.addAttribute("userInfo", enrolled);
			// model.setStatus(HttpStatus.CREATED);
        } catch (RuntimeException re) {
            log.error("RuntimeException message: {}", re.getMessage());

            if(re.getMessage().contains("Conflicted")) {
				viewName = "/user/enrollDup";
				model.addAttribute("userInfo", userInfo);
				// model.setStatus(HttpStatus.CONFLICT);
			} else {
				throw re;
			}
        }

        return viewName;
    }

}
