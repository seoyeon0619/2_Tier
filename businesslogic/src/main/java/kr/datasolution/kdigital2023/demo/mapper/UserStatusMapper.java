package kr.datasolution.kdigital2023.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import kr.datasolution.kdigital2023.demo.model.UserStatus;

@Mapper
public interface UserStatusMapper {
	@Select("SELECT * FROM user_status")
	public List<UserStatus> findAll();
	
	@Select("SELECT * FROM user_status WHERE id = #{id}")
	public UserStatus findById(@Param("id") Integer id);
	
	@Select("SELECT * FROM user_status WHERE status = #{status}")
	public UserStatus findByStatus(@Param("status") String status);
}
