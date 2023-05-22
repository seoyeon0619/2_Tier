package kr.datasolution.kdigital2023.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import kr.datasolution.kdigital2023.demo.model.User;
import kr.datasolution.kdigital2023.demo.model.UserStatus;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private RestTemplate restTemplate;

    @Value("${urls.scheme}://${urls.host}:${urls.port}${urls.prefix}${urls.user.prefix}")
    private String baseUrl; // http://localhost:8088/api/user
    @Value("${urls.scheme}://${urls.host}:${urls.port}${urls.prefix}${urls.user.prefix}${urls.user.status}")
    private String statusUrl; // http://localhost:8088/api/user-status

    @Autowired
    public UserService(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    public User enrollUser(User userInfo) {
        log.debug(baseUrl);
        ResponseEntity<User> response = restTemplate.exchange(baseUrl, HttpMethod.POST,  new HttpEntity<User>(userInfo), new ParameterizedTypeReference<User>() {});
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else if (response.getStatusCode().equals(HttpStatus.CONFLICT)) {
            throw new RuntimeException("User Conflicted");
        } else {
            throw new RuntimeException("Unknown Internal Error");
        }
    }

    public List<User> getAllUsers() {
        ResponseEntity<List<User>> response = restTemplate.exchange(baseUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        if (!response.getStatusCode().is2xxSuccessful()) {
            return null;
        }

        return response.getBody();
    }

    public User getUser(String userId) {
        return restTemplate.getForObject(baseUrl + "/" + userId, User.class);
        // ResponseEntity<User> response = restTemplate.exchange(baseUrl + "/" + userId, HttpMethod.GET, null, new ParameterizedTypeReference<User>() {} );
        // return response.getBody();
    }

    public User updateUser(String userId, User userInfo) {
        if(userId.equals(userInfo.getUserId())) {
            ResponseEntity<User> response = restTemplate.exchange(baseUrl + "/" + userId, HttpMethod.PUT, new HttpEntity<User>(userInfo), new ParameterizedTypeReference<User>() {});
            if(response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
        }

        return this.getUser(userId);
    }

    public Boolean deleteUser(String userId) {
        Boolean ret = true;
        try {
            restTemplate.delete(baseUrl + "/" + userId);
        } catch (RestClientException e) {
            log.error(e.getMessage());
            ret = false;
        }
        return ret;
    }

    public List<UserStatus> getAllUserStatus() {
        ResponseEntity<List<UserStatus>> response = restTemplate.exchange(statusUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<UserStatus>>() {});
        if (response.getStatusCode().isError()) {
            return null;
        }

        return response.getBody();
    }
}
