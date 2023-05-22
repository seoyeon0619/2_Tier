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

import kr.datasolution.kdigital2023.demo.model.Department;

@Service
public class DepartmentService {
    private static final Logger log = LoggerFactory.getLogger(DepartmentService.class);

    private RestTemplate restTemplate;

    @Value("${urls.scheme}://${urls.host}:${urls.port}${urls.prefix}${urls.department.prefix}")
    private String baseUrl; // http://localhost:8088/api/department

    @Autowired
    public DepartmentService(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }
    
    public List<Department> getAllDepartment() {
        log.debug(baseUrl);
        ResponseEntity<List<Department>> response = restTemplate.exchange(baseUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Department>>() {});
        if (response.getStatusCode().isError()) {
            return null;
        }

        return response.getBody();
    }

    public Department addDepartment(Department department) {
        Department resDepartment = null;
        try {
            resDepartment = restTemplate.postForObject(baseUrl, department, Department.class);
        } catch (RestClientException e) {
            log.error(e.getMessage());
        }

        return resDepartment;
    }

    public Boolean delDepartment(Integer departmentId) {
        Boolean ret = true;
        try {
            restTemplate.delete(baseUrl + "/" + departmentId.toString());
        } catch (RestClientException e) {
            log.error(e.getMessage());
            ret = false;
        }
        return ret;
    }
}
