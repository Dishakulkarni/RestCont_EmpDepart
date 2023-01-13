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

import com.cg.rest.entity.Department;
import com.cg.rest.entity.Employee;
import com.cg.rest.exception.NoSuchDepartmentFoundException;
import com.cg.rest.services.DepartmentService;

@RestController
public class DepartmentRestController {
	
	@Autowired
	private DepartmentService dService;
	
	@GetMapping("/dep")
	public ResponseEntity<List<Department>> findAllDepartment(){
		try {
			List<Department> elist=dService.findAllDepartment();
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
	
	@PostMapping("/addDep")
	public ResponseEntity<Department> addDepartment(@RequestBody Department d1){
		try {
			Department deptt = dService.addDepartment(d1);
			return new ResponseEntity<>(deptt,HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deletedep/{id}")
	public ResponseEntity<String> deleteEmp(@PathVariable("id")int id){
		try {
			boolean success = dService.removeDepartment(id);
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
	
	@PutMapping("/updatedep/{id}")
	public ResponseEntity<Department> updateDept(@RequestBody Department dep, @PathVariable int id){
		try {
			return new ResponseEntity<>(dService.updateDepartment(dep, id), HttpStatus.CREATED);
		}
		catch(NoSuchDepartmentFoundException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
