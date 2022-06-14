package com.ecommerce.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.ecommerce.dto.LoginDto;
import com.ecommerce.dto.UpdatePasswordDto;
import com.ecommerce.dto.UpdateUserDetailesDto;
import com.ecommerce.model.User;



public interface UserService {
  
	JSONObject saveUser(User user);

	List<User> getUser();

	JSONObject updateUser(UpdateUserDetailesDto updateUserDto, long id);

	JSONObject delateUser(long id);

	JSONObject verifylogin(LoginDto users);


	User getUserById(long id);

	

	JSONObject delateUser(Long id);

	

	JSONObject getUserById(Long i);

	JSONObject updateUserPassword(UpdatePasswordDto updatePasswordDto, Long id);

	JSONObject updateUser(UpdateUserDetailesDto updateUserDto, Long id);

	JSONObject updateUserPassword(UpdatePasswordDto uDto, long id);

	
	

}
