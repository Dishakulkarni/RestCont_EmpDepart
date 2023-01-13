package com.cg.rest.services;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.rest.dao.EmployeeRepository;
import com.cg.rest.entity.Employee;
import com.cg.rest.exception.NoSuchEmployeeFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository empRepo;
		
	@Override
	@Transactional
	public Employee addEmployee(Employee emp) {
		return empRepo.save(emp);
	}

	@Override
	public List<Employee> findAllEmployee() {
		return empRepo.findAll();
	}

	@Override
	public Employee findEmployeeById(int id) throws NoSuchEmployeeFoundException {
		Optional<Employee> emp = empRepo.findById(id);
		if(emp.isPresent())
			return emp.get();
		
		throw new NoSuchEmployeeFoundException("No found with id : "+id);
	}

	@Override
	@Transactional
	public Employee modifyEmployee(Employee emp, int id) throws NoSuchEmployeeFoundException {
		Employee findEmp = findEmployeeById(id);
		findEmp.setEmpName(emp.getEmpName());
		findEmp.setDept(emp.getDept());
		findEmp.setEmpRole(emp.getEmpRole());
		findEmp.setEmpSal(emp.getEmpSal());
		return empRepo.save(findEmp);
	}

	@Override
	public boolean removeEmployee(int id) throws NoSuchEmployeeFoundException {
		empRepo.deleteById(id);
		Optional<Employee> emp = empRepo.findById(id);
		if(emp.isPresent())
			return false;
		else
			return true;
	}

	@Override
	public List<Employee> findEmpByDept(String departmentName) {
		return empRepo.findEmpByDeptId(departmentName);
	}

}
