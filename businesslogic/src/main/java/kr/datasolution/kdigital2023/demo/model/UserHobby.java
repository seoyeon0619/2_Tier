package kr.datasolution.kdigital2023.demo.model;

import java.util.Objects;

public class UserHobby {
	private Integer userId;
	private Integer hobbyId;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getHobbyId() {
		return hobbyId;
	}
	public void setHobbyId(Integer hobbyId) {
		this.hobbyId = hobbyId;
	}
	@Override
	public String toString() {
		return "UserHobby [userId=" + userId + ", hobbyId=" + hobbyId + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(hobbyId, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserHobby other = (UserHobby) obj;
		return Objects.equals(hobbyId, other.hobbyId) && Objects.equals(userId, other.userId);
	}
	
	
}
