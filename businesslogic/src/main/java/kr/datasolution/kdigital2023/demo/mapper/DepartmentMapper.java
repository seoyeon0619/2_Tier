package kr.datasolution.kdigital2023.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import kr.datasolution.kdigital2023.demo.model.Department;

@Mapper
public interface DepartmentMapper {
	@Select("SELECT * FROM department")
	public List<Department> findAll();

	@Select("SELECT * FROM department WHERE id = #{id}")
	public Department findById(@Param("id") Integer id);
	
	@Insert("INSERT INTO department (id, department) VALUES (#{id}, #{department})")
	public Integer save(Department department);
	
	@Delete("DELETE FROM department WHERE id = #{departmentId}")
	public Integer remove(@Param("departmentId") Integer departmentId);
}
