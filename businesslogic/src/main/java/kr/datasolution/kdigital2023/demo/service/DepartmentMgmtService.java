package kr.datasolution.kdigital2023.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.datasolution.kdigital2023.demo.mapper.DepartmentMapper;
import kr.datasolution.kdigital2023.demo.model.Department;

@Service
public class DepartmentMgmtService {
    @Autowired
   private DepartmentMapper departmentMapper;
   
   public List<Department> getAllDepartment() {
        return departmentMapper.findAll();
    }

    public Department getDepartment(Integer departmentId) {
        return departmentMapper.findById(departmentId);
    }

    public Integer addDepartment(Department department) {
        return departmentMapper.save(department);
    }

    public Integer delDepartment(Integer departmentId) {
        return departmentMapper.remove(departmentId);
    }   
}
