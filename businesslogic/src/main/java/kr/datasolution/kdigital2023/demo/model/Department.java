package kr.datasolution.kdigital2023.demo.model;

import java.util.Objects;

public class Department {
	private Integer id;
	private String department;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Override
	public int hashCode() {
		return Objects.hash(department, id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return Objects.equals(department, other.department) && Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", department=" + department + "]";
	}
	
	
}
