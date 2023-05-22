package kr.datasolution.kdigital2023.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import kr.datasolution.kdigital2023.demo.model.Hobby;

@Mapper
public interface HobbiesMapper {
	@Select("select * from hobbies")
	public List<Hobby> findAll();
	
	@Insert("INSERT INTO hobbies (hobby) VALUES (#{hobby})")
	public Integer save(@Param("hobby") String hobby);
	
	@Delete("DELETE FROM hobbies WHERE id = #{hobbyId}")
	public Integer remove(@Param("hobbyId") Integer hobbyId);
}
