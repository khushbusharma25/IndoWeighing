package com.ind.weighing.Indo_weighing.controller;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ind.weighing.Indo_weighing.domain.UserDTO;
import com.ind.weighing.Indo_weighing.service.UserService;

@Component
public class ValidationController implements Validator{
	 @Autowired
	 private UserService userService;
	@Override
	public boolean supports(Class<?> paramClass) {
		// TODO Auto-generated method stub
		return UserDTO.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		UserDTO userDTO =(UserDTO) obj;
		if(userDTO.getContNo().length() >10 || userDTO.getContNo().length()<10) {
			errors.reject("Enter valid Contact Number");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fName", "fName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lName", "lName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "address.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "userName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
		 if (userService.findByUserName(userDTO.getUsername()) != null) {
	            errors.rejectValue( "userName", "Duplicate.userForm.username");
	        }
		 Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
		            Pattern.CASE_INSENSITIVE);
		      if (!(pattern.matcher(userDTO.getEmail()).matches())) {
		         errors.rejectValue("email", "user.email.invalid");
		      }
	}

}
