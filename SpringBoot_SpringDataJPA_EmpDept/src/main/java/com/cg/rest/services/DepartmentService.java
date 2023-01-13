package com.cg.rest.services;

import java.util.List;

import com.cg.rest.entity.Department;
import com.cg.rest.entity.Employee;
import com.cg.rest.exception.NoSuchDepartmentFoundException;
import com.cg.rest.exception.NoSuchEmployeeFoundException;

public interface DepartmentService {
	
	List<Department> findAllDepartment();
	Department addDepartment(Department dep);
	Department getDepartmentById(int id) throws NoSuchDepartmentFoundException;
	boolean removeDepartment(int id) throws NoSuchDepartmentFoundException;
	Department updateDepartment(Department dept, int id) throws NoSuchDepartmentFoundException;
}
