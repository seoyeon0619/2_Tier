package kr.datasolution.kdigital2023.demo.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

//public record UserInfo(String userId, String userName, String phone, String email, List<int> hobbies) {
//}
public class User {
	private String userId; 
	private String userName;
	private String phoneNo;
	private String email;
	private Integer statusId;
	private String status;
	private Integer departmentId;
	private String department;
	private List<Integer> hobbies;
	private Date enroll;
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<Integer> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<Integer> hobbies) {
		this.hobbies = hobbies;
	}


	public Date getEnroll() {
		return enroll;
	}

	public void setEnroll(Date enroll) {
		this.enroll = enroll;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", phoneNo=" + phoneNo + ", email=" + email
				+ ", statusId=" + statusId + ", status=" + status + ", departmentId=" + departmentId + ", department="
				+ department + ", hobbies=" + hobbies + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, hobbies, phoneNo, userId, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(hobbies, other.hobbies)
				&& Objects.equals(phoneNo, other.phoneNo) && Objects.equals(userId, other.userId)
				&& Objects.equals(userName, other.userName);
	}


	
}
