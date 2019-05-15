package com.ind.weighing.Indo_weighing.service.impl;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.ind.weighing.Indo_weighing.domain.CustomSequences;
import com.ind.weighing.Indo_weighing.domain.UserDTO;
import com.ind.weighing.Indo_weighing.repository.RoleRepo;
import com.ind.weighing.Indo_weighing.repository.UserRepo;
import com.ind.weighing.Indo_weighing.service.SequenceService;
import com.ind.weighing.Indo_weighing.service.UserService;
import com.ind.weighing.Indo_weighing.utils.JWTUtils;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo repo;
	
	@Autowired
	SequenceService sequenceService ;
	
	
	
	@Autowired
	JWTUtils jwtUtils; 
	
	@Autowired
	RoleRepo role;
	public Map<String, Object> saveStudent(UserDTO userDTO) {
		Map<String, Object> resp = new HashMap<>();
		int count = repo.countByUsername(userDTO.getUsername());
		int countEmail = repo.countByEmail(userDTO.getEmail());
		if(count>=1) {
			resp.put("status", 2);
			resp.put("message", "Username already exist, Please try again");
		}
		else if(countEmail>=1) {
			resp.put("status", 2);
			resp.put("message", "Email already exist,Please try again");
		}else {
			userDTO.setUser_id(sequenceService.getNextSequence("customSequences"));
			repo.save(userDTO);
			resp.put("status", 1);
			resp.put("message", "Successfully Registerd, please login");
		}
		return resp;
	}
	
	public List<UserDTO> saveStudents(List<UserDTO> userDTO) {
		
		return repo.saveAll(userDTO);
	}
	
	public  List<UserDTO> getAllStudents() {
//		List<StudentDTO> listStudent=  studentRepo.findAll();
//		Map<String,List<StudentDTO>> map= new HashMap<String, List<StudentDTO>>(); 
//		map.put("studentList", listStudent);
//		return map;
		return  repo.findAll();
	}
	
	public UserDTO getStudentByKey(Long id) {
	
		return repo.findById(id).get();
	}

	public UserDTO updateStudent(UserDTO userDTO) {
		UserDTO dto = repo.findByUserId(userDTO.getUser_id()).get();
		dto.setfName(userDTO.getfName());
		dto.setlName(userDTO.getlName());
		dto.setEmail(userDTO.getEmail());
		dto.setAddress(userDTO.getAddress());
		dto.setContNo(userDTO.getContNo());
		
		return repo.save(dto);
	}
	
	
	public ResponseEntity<?> deleteStudent(Long id) {
		UserDTO userDTO = repo.findById(id).get();
		 repo.delete(userDTO);
		return null;
		
	}
	
	public Map<String, Object> authnticate(UserDTO userDTO) {
		Map<String, Object> resp =new HashMap<>();
		String token=null;
		resp.put("status", 0);
		resp.put("message", "Username or password is incorrect");
		userDTO = repo.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
	//	UserDetails userDetails = userDeatilsService.loadUserByUsername(userDTO.getUsername());
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		UserDetails userDetails=null;
		if(userDTO!=null) {
			 userDetails = new org.springframework.security.core.userdetails.User(userDTO.getUsername(), 
				userDTO.getPassword(), grantedAuthorities);
			 token= jwtUtils.generateToken(userDetails);
			 resp.put("status", 1);
			 resp.put("message", "Successfully Loged in");
		}
		resp.put("token", token);
		return resp;
		
	}
	
	public UserDTO findByUserName(String username) {
		UserDTO userDTO = repo.findByUsername(username);
		
		return userDTO;
		
	}

	@Override
	public UserDTO findByFName(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return repo.findByFName(userDTO.getfName());
	}

//	@Override
//	public List<UserDTO> search(UserDTO userDTO) {
//		// TODO Auto-generated method stub
//		return repo.search(userDTO.getfName());
//	}

	@Override
	public List<UserDTO> searchByAge(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return repo.findByAgeGreaterThan(userDTO.getAge());
	}

	@Override
	public List<UserDTO> searchBylastName(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return repo.findByLNameLike(userDTO.getlName());
	}

	@Override
	public List<UserDTO> findByLNameOrFName(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return repo.findByLNameOrFName(userDTO.getlName(), userDTO.getfName());
	}

	@Override
	public List<UserDTO> findByAddress(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return repo.findByAddress(userDTO.getAddress());
	}

	@Override
	public int deleteByfName(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return repo.deleteByfName(userDTO.getfName());
	}

	@Override
	public int updateBylName(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return 0; // repo.updateByLName(userDTO.getfName(), userDTO.getAddress(), userDTO.getlName());
	}
	
	
	
	
}
