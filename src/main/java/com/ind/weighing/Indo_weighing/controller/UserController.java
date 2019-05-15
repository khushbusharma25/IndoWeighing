package com.ind.weighing.Indo_weighing.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ind.weighing.Indo_weighing.domain.UserDTO;
import com.ind.weighing.Indo_weighing.service.UserService;
import com.ind.weighing.Indo_weighing.utils.JWTUtils;

@RestController

public class UserController {

	@Autowired
	UserService userService;
	
	
	
//	@Qualifier("validationController")
//	private Validator validator;
//	
//	@InitBinder
//	private void initBinder(WebDataBinder binder) {
//		binder.setValidator(new ValidationController());
//	}
	
	
	@PostMapping("/auth")
	public Map<String, Object> authenticate(@Valid @RequestBody UserDTO userDTO){
		return userService.authnticate(userDTO);
	}
	
	
	@GetMapping("/getAll")
		
	List<UserDTO> list(HttpServletRequest request){
		System.out.println(request.getHeader("token"));
		Map<String,Object> resp=new HashMap<>();
			List<UserDTO> users = userService.getAllStudents();
			resp.put("data", users);
			resp.put("status", 2);
			return users;
			
	}
	
	@PostMapping("/save")
	public Map<String, Object> saveStudent(@Valid @RequestBody UserDTO userDTO) {
			return userService.saveStudent(userDTO);
		
	}
	@PostMapping("/saveList")
	public String saveStudents(@Valid @RequestBody List<UserDTO >userDTO, BindingResult result) {
		if(result.hasErrors()) {
			return "fail to save";
		}else {
			userService.saveStudents(userDTO);
			return "Success";
		}
		 
		
	}
	@GetMapping("/getByKey/{id}")
	public UserDTO getStudentById(@PathVariable(value="id") Long id) {
		return userService.getStudentByKey(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable(value="id") Long id) {
		return userService.deleteStudent(id);
	
	}
	
	@PutMapping("/updateStudent")
	public String updateStudent(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
		if(result.hasErrors()) {
			return "fail to updated";
		}else {
			userService.updateStudent(userDTO);
			return "Successfully Updated";
		}
	}
	
	@PostMapping("/getByfname")
	public UserDTO getByFname(@Valid @RequestBody UserDTO userDTO) {
		return userService.findByFName(userDTO);
	}
	
//	@PostMapping("/search")
//	public List<UserDTO> search(@Valid @RequestBody UserDTO userDTO) {
//		return userService.search(userDTO);
//	}
	
	@PostMapping("/searchBylName")
	public List<UserDTO> searchByLName(@Valid @RequestBody UserDTO userDTO) {
		return userService.searchBylastName(userDTO);
	} 
	@PostMapping("/searchAge")
	public List<UserDTO> searchByAge(@Valid @RequestBody UserDTO userDTO) {
		return userService.searchByAge(userDTO);
	}
	
	@PostMapping("/searchforlname")
	public List<UserDTO> findByFNmeOrLname(@Valid @RequestBody UserDTO userDTO) {
		return userService.findByLNameOrFName(userDTO);
	}
	
	@PostMapping("/getByAdd")
	public List<UserDTO> getByAddress(@Valid @RequestBody UserDTO userDTO) {
		return userService.findByAddress(userDTO);
	}
	
	@PostMapping("/deleteByFname") //not available
	public String deleteByFname(@Valid @RequestBody UserDTO userDTO) {
		 userService.deleteByfName(userDTO);
		 return "deleted";
	}
	
	@PostMapping("/updateBylName") 
	public String updateBylName(@Valid @RequestBody UserDTO userDTO) {
		 int i =userService.updateBylName(userDTO);
		 if(i !=0)
		 return "updated";
		 else {
			 return "failed";
		 }
	}
}
