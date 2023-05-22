package kr.datasolution.kdigital2023.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.datasolution.kdigital2023.demo.model.User;

@Mapper
public interface UsersMapper {
	public List<User> findAll();
	
	public Integer save(User user);
	
	public User find(@Param("userId") String userId);
	
	public Integer update(User user);

	public Integer remove(@Param("userId") String userId);
}
