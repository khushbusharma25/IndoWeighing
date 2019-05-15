package com.ind.weighing.Indo_weighing.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ind.weighing.Indo_weighing.domain.AdminDTO;
import com.ind.weighing.Indo_weighing.repository.AdminRepo;
import com.ind.weighing.Indo_weighing.service.AdminService;


@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminRepo repo;
	
	public AdminDTO saveAdmin(AdminDTO adminDTO) {
		return repo.save(adminDTO);
	}
	
	public  List<AdminDTO> getAllAdmin() {
//		List<StudentDTO> listStudent=  studentRepo.findAll();
//		Map<String,List<StudentDTO>> map= new HashMap<String, List<StudentDTO>>(); 
//		map.put("studentList", listStudent);
//		return map;
		return  repo.findAll();
	}
	
	public AdminDTO getAdminById(Long id) {
		Optional<AdminDTO> admin = repo.findById(id);
		int q = 1/0;
		System.out.println(q);
		return admin.get();
	}

	public AdminDTO updateAdmin(AdminDTO adminDTO) {
		Optional<AdminDTO> dtoOptional = repo.findById(adminDTO.getId());
		AdminDTO dto = dtoOptional.get();
		dto.setfName(adminDTO.getfName());
		dto.setlName(adminDTO.getlName());
		dto.setEmail(adminDTO.getEmail());
		dto.setContNo(adminDTO.getContNo());
		
		return repo.save(dto);
	}
	
	
	public ResponseEntity<?> deleteAdmin(Long id) {
		Optional<AdminDTO> adminDTO = repo.findById(id);
		AdminDTO admin = adminDTO.get();
		 repo.delete(admin);
		return null;
		
	}
	
	
}
