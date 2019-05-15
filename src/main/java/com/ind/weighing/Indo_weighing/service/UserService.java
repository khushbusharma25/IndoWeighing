package com.ind.weighing.Indo_weighing.service;


import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ind.weighing.Indo_weighing.domain.UserDTO;


@Service
public interface UserService {
	Map<String, Object> saveStudent(UserDTO userDTO);
	
	List<UserDTO> saveStudents(List<UserDTO> userDTO);
	
	List<UserDTO> getAllStudents();
	
	UserDTO getStudentByKey(Long id);
	
	UserDTO updateStudent(UserDTO userDTO);
	
	ResponseEntity<?> deleteStudent(Long id);
	
	Map<String, Object> authnticate(UserDTO userDTO);

	//List<UserDTO> search(UserDTO userDTO);

	List<UserDTO> searchBylastName(UserDTO userDTO);
	
	List<UserDTO> searchByAge(UserDTO userDTO);

	UserDTO findByFName(UserDTO userDTO);
	
	List<UserDTO> findByLNameOrFName(UserDTO userDTO);
	
	List<UserDTO> findByAddress(UserDTO userDTO);
	
	int deleteByfName(UserDTO userDTO);
	
	int updateBylName(UserDTO userDTO);
	
	UserDTO findByUserName(String username);
}
