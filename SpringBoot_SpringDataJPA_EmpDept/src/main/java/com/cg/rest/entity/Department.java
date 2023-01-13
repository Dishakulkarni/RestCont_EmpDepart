package com.cg.rest.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//dept is owner entity. it owns employees

@Entity
@Table(name = "DeptMapTable")
public class Department {

	@Id
	private int deptId;
	private String deptName;

	@OneToMany(mappedBy = "dept", cascade = CascadeType.ALL)
	private List<Employee> elist;

	public Department() {
	}

	public Department(int deptId, String deptName) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
	}

	public Department(List<Employee> elist) {
		super();
		this.elist = elist;
		  for(Employee e:elist) 
		  { 
			  e.setDept(this); 
		  }
		  
		 
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName + "]";
	}

}
