package kr.datasolution.kdigital2023.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import kr.datasolution.kdigital2023.demo.model.Hobby;

@Service
public class HobbyService {
    private static final Logger log = LoggerFactory.getLogger(HobbyService.class);

    private RestTemplate restTemplate;

    @Value("${urls.scheme}://${urls.host}:${urls.port}${urls.prefix}${urls.hobby.prefix}")
    private String baseUrl; // http://localhost:8088/api/hobby

    @Autowired
    public HobbyService(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    public List<Hobby> getHobbies() {
        log.debug(baseUrl);
        ResponseEntity<List<Hobby>> response = restTemplate.exchange(baseUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Hobby>>() {});
        if (!response.getStatusCode().is2xxSuccessful()) {
            // need exception handling
            return null;
        }
        return response.getBody();
    }

    public Hobby addHobby(Hobby hobby) {
        Hobby resHobby = null;
        try {
            resHobby = restTemplate.postForObject(baseUrl, hobby, Hobby.class);
        } catch (RestClientException e) {
            log.error(e.getMessage());
        }

        return resHobby;
    }

    public Boolean deleteHobby(Integer hobbyId) {
        Boolean ret = true;
        try {
            restTemplate.delete(baseUrl + "/" + hobbyId.toString());
        } catch (RestClientException e) {
            log.error(e.getMessage());
            ret = false;
        }
        return ret;
    }
}
