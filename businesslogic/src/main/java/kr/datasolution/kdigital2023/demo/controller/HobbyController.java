package kr.datasolution.kdigital2023.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.datasolution.kdigital2023.demo.model.Hobby;
import kr.datasolution.kdigital2023.demo.service.HobbyMgmtService;

@RestController
@RequestMapping("/api/hobby")
public class HobbyController {

    @Autowired
    private HobbyMgmtService hobbyMgmtService;
    
    @GetMapping("")
    public List<Hobby> getHobbies() {
        return hobbyMgmtService.getHobbies();
    }

    @PostMapping("")
    public Hobby addHobby(@RequestBody Hobby hobby) {
        try {
            hobbyMgmtService.addHobby(hobby);
            return hobby;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @DeleteMapping("/{hobbyId}")
    public Integer deleteHobby(@PathVariable("hobbyId") Integer hobbyId) {
        return hobbyMgmtService.deleteHobby(hobbyId);
    }
}
