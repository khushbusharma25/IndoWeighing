package com.ind.weighing.Indo_weighing.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ind.weighing.Indo_weighing.domain.AdminDTO;
import com.ind.weighing.Indo_weighing.service.AdminService;



@RestController
@RequestMapping("/ad")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@GetMapping("/adminhome")
	public String home() {
		return "Welcome to admin page";
	}
	
	@PostMapping("/admin")
	public AdminDTO save(@Valid @RequestBody AdminDTO adminDTO) {
		return adminService.saveAdmin(adminDTO);
	}
	
	@GetMapping("/admin")
	public List<AdminDTO> getAll() {
		return adminService.getAllAdmin();
	}
	
	@GetMapping("/admin/{id}")
	public AdminDTO getById(@PathVariable(name="id") Long id) {
		return adminService.getAdminById(id);
	}
	
	@PutMapping("/admin")
	public AdminDTO	update(@Valid @RequestBody AdminDTO adminDTO) {
		return adminService.updateAdmin(adminDTO);
	}
	
	@DeleteMapping("/admin/{id}")
	public ResponseEntity<?> delete(@PathVariable(name="id") Long id) {
		return adminService.deleteAdmin(id);
	}
		
}

