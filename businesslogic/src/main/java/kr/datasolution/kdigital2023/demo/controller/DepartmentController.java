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

import kr.datasolution.kdigital2023.demo.model.Department;
import kr.datasolution.kdigital2023.demo.service.DepartmentMgmtService;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentMgmtService departmentMgmtService;

    @GetMapping("")
    public List<Department> getAllDepartment() {
        return departmentMgmtService.getAllDepartment();
    }

    @GetMapping("/{departmentId}")
    public Department getDepartment(@PathVariable("departmentId") Integer departmentId) {
        return departmentMgmtService.getDepartment(departmentId);
    }

    @PostMapping("")
    public Department addDepartment(@RequestBody Department department) {
        try {
            departmentMgmtService.addDepartment(department);
            return department;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @DeleteMapping("/{departmentId}")
    public Integer delDepartment(@PathVariable("departmentId") Integer departmentId) {
        return departmentMgmtService.delDepartment(departmentId);
    }
}
