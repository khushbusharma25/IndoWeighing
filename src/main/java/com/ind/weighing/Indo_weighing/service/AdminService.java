package com.ind.weighing.Indo_weighing.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ind.weighing.Indo_weighing.domain.AdminDTO;



public interface AdminService {
	AdminDTO saveAdmin(AdminDTO adminDTO);
	
	List<AdminDTO> getAllAdmin();
	
	AdminDTO getAdminById(Long id);
	
	AdminDTO updateAdmin(AdminDTO adminDTO);
	
	ResponseEntity<?> deleteAdmin(Long id);
}
