package kr.datasolution.kdigital2023.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.datasolution.kdigital2023.demo.mapper.HobbiesMapper;
import kr.datasolution.kdigital2023.demo.model.Hobby;

@Service
public class HobbyMgmtService {
    private static final Logger log = LoggerFactory.getLogger(HobbyMgmtService.class);

    @Autowired
    private HobbiesMapper hobbiesMapper;

    public List<Hobby> getHobbies() {
        List<Hobby> hobbies = hobbiesMapper.findAll();
        log.debug("hobbies : {}", hobbies);
        return hobbies;
    }

    public Integer addHobby(Hobby hobby) {
        return hobbiesMapper.save(hobby.getHobby());
    }

    public Integer deleteHobby(Integer hobbyId) {
        return hobbiesMapper.remove(hobbyId);
    }
}
