package kr.datasolution.kdigital2023.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import kr.datasolution.kdigital2023.demo.model.UserHobby;

@Mapper
public interface UserHobbiesMapper {
	@Select("SELECT * FROM user_hobbies")
	public List<UserHobby> findAll();
	
	@Insert("INSERT INTO user_hobbies (user_id, hobby_id) VALUES (#{userId}, #{hobbyId})")
	public Integer saveUserHobboy(@Param("userId") String userId, @Param("hobbyId") Integer hobbyId);
	
	@Select("SELECT hobby_id FROM user_hobbies WHERE user_id = #{userId}")
	public List<Integer> findHobbyIdByUserId(@Param("userId") String userId);
}
