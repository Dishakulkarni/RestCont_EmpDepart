package com.cg.rest.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cg.rest.entity.Employee;
import com.cg.rest.exception.NoSuchEmployeeFoundException;
import com.cg.rest.services.EmployeeService;

@RestController
public class EmployeeRestController {
	
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/emp")
	public ResponseEntity<List<Employee>> findAllEmployee(){
		try {
			List<Employee> elist=empService.findAllEmployee();
			if(elist.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else {
				return new ResponseEntity<>(elist,HttpStatus.OK);
			}
			
		}
		catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/addemp")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee e1){
		try {
			Employee emp = empService.addEmployee(e1);
			return new ResponseEntity<>(emp,HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/empbyid/{id}")
	public ResponseEntity<Employee> findEmployeeById(@PathVariable("id")int id) throws NoSuchEmployeeFoundException{
		Employee emp = empService.findEmployeeById(id);
		if(emp!=null) {
			return new ResponseEntity<>(emp,HttpStatus.OK);
		}
		else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);

		}	
	}
	
	@PutMapping("/modifyemp/{id}")
	public ResponseEntity<Employee> modifyEmployee(@RequestBody Employee newemp, @PathVariable("id")int id){
		try {
			return new ResponseEntity<>(empService.modifyEmployee(newemp, id),HttpStatus.ACCEPTED);
			
		}
		catch(NoSuchEmployeeFoundException e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);

		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deleteemp/{id}")
	public ResponseEntity<String> deleteEmp(@PathVariable("id")int id){
		try {
			boolean success = empService.removeEmployee(id);
			if(success) {
				return new ResponseEntity<>("Deleted",HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("Failed to Delete.",HttpStatus.NOT_MODIFIED);
			}
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/empbydept/{deptName}")
	public ResponseEntity<List<Employee>> findEmpByDept(@PathVariable("deptName")String deptName){
		try {
			return new ResponseEntity<>(empService.findEmpByDept(deptName),HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
