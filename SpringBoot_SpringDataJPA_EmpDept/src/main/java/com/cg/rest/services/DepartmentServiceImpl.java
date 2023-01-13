package com.cg.rest.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.rest.dao.DepartmentRepository;
import com.cg.rest.entity.Department;
import com.cg.rest.entity.Employee;
import com.cg.rest.exception.NoSuchDepartmentFoundException;
import com.cg.rest.exception.NoSuchEmployeeFoundException;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepository depRepo;
	
	@Override
	@Transactional
	public List<Department> findAllDepartment() {
		// TODO Auto-generated method stub
		return depRepo.findAll();
	}

	@Override
	public Department addDepartment(Department dep) {
		// TODO Auto-generated method stub
		return depRepo.save(dep);
	}

	@Override
	public boolean removeDepartment(int id) throws NoSuchDepartmentFoundException {
		depRepo.deleteById(id);
		Optional<Department> dep = depRepo.findById(id);
		if(dep.isPresent())
			return false;
		else
			return true;
	}

	@Override
	public Department getDepartmentById(int id) throws NoSuchDepartmentFoundException {
		Optional<Department> dep = depRepo.findById(id);
		if(dep.isPresent())
			return dep.get();
		
		throw new NoSuchDepartmentFoundException("No found with id : "+id);
	}
	
	public Department updateDepartment(Department dept, int id) throws NoSuchDepartmentFoundException{
		Department findDep = getDepartmentById(id);
		findDep.setDeptId(dept.getDeptId());
		findDep.setDeptName(dept.getDeptName());
		return depRepo.save(findDep);
	}
}
