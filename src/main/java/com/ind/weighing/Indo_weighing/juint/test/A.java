package com.ind.weighing.Indo_weighing.juint.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ind.weighing.Indo_weighing.service.AdminService;

public class A {
	@Autowired 
	AdminService adminService;
	String message;
	@Test(expected = ArithmeticException.class)
	public void testJUnitMessage(){

		System.out.println("Junit Message is printing ");
		adminService.getAdminById(1L);
		int a= 1/0;
//		junitMessage.printMessage();

	}

	@Test
	public void testJUnitHiMessage(){ 
		message="Hi!" + message;
		System.out.println("Junit Message is printing ");
	
	}
}
