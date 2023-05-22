package kr.datasolution.kdigital2023.demo.model;

import java.util.Objects;

import org.springframework.lang.Nullable;

//public record Hobbies(
//		Long id,
//		String hobby) {
//}

public class Hobby {
	private Integer id;
	@Nullable
	private String hobby;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	@Override
	public int hashCode() {
		return Objects.hash(hobby, id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hobby other = (Hobby) obj;
		return Objects.equals(hobby, other.hobby) && Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "Hobbies [id=" + id + ", hobby=" + hobby + "]";
	}
	
	
}