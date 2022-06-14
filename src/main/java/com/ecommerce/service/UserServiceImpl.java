package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.LoginDto;
import com.ecommerce.dto.UpdatePasswordDto;
import com.ecommerce.dto.UpdateUserDetailesDto;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;


@Service
public class UserServiceImpl {
	private String ok="ok";
	private String notOk="Not Ok";
		
	@Autowired
	private UserRepository userRepo;



	public JSONObject userResponse(String status, String message, String accessToken) {
		JSONObject obj=new JSONObject();
		obj.put("status",status);
		obj.put("message",message);
		obj.put("accessToken",accessToken);
		return obj;
	}
	
	
	
	
	public JSONObject saveUser(User user) {
		UserServiceImpl userServ=new UserServiceImpl();
		Optional<User> optionalUser = userRepo.findById(user.getId());
		
		try {
			
		if (optionalUser.isPresent()) {
		return userServ.userResponse(notOk,"User with id already exists" , "eyJhbGciOiJIUzI1NiIsInR5a1...");

		} else {

		userRepo.save(user);
		return userServ.userResponse(ok,"User is registered successfully" , "eyJhbGciOiJIUzI1NiIsInR5a1...");

		}
		}
		catch (Exception e){
		return userServ.userResponse("Exception",e.getMessage() , "eyJhbGciOiJIUzI1NiIsInR5a1...");

		}
		}
	
		public List<User> getUser() {
		return userRepo.findAll();
		}
	
	public JSONObject updateUser(UpdateUserDetailesDto updateUserDto, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public JSONObject delateUser(Long i) {
		// TODO Auto-generated method stub
		return null;
	}

	public JSONObject verifylogin(LoginDto users) {
		String email = users.getEmail();
		String password = users.getPassword();

		User user = userRepo.findByEmail(email);
		UserServiceImpl userServ=new UserServiceImpl();
		if(user!=null) {
		if(user.getEmail().equals(email)&& user.getPassword().equals(password))
		{
		return userServ.userResponse(ok,"Log In was succesfull" , "eyJhbGciOiJIUzI1NiIsInR5c3...");
		}
		else
		{
		return userServ.userResponse(notOk,"Please enter valid user name and password" , "eyJhbGciOiJIUzI1NiIsInR5d4...");
		}
		}
		else
		{
		return userServ.userResponse(notOk,"User does not exists Please enter valid user name" , "eyJhbGciOiJIUzI1NiIsInR5f5...");

		}
		}



	public JSONObject updateUserPassword(UpdatePasswordDto uDto, long i) {
		// TODO Auto-generated method stub
		return null;
	}

	public JSONObject getUserById(Long i) {
		// TODO Auto-generated method stub
		return null;
	}

	public JSONObject updateUser(UpdateUserDetailesDto user, long id) {
		Optional<User> findById = userRepo.findById(id);
		UserServiceImpl userServ=new UserServiceImpl();
		if(findById.isPresent())
		{
		User user1 = findById.get();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String email = user.getEmail();
		user1.setFirstName(firstName);
		user1.setLastName(lastName);
		user1.setEmail(email);
		userRepo.save(user1);
		return userServ.userResponse(ok,"User Details Updated" , "eyJhbGciOiJIUzI1NiIsInR5dCh7...");
		}
		else
		{
		return userServ.userResponse(notOk,"User does not exists So unable to update" , "eyJhbGciOiJIUzI1NiIsInR5dC2i8...");
		}
		}


	public JSONObject delateUser(long id) {
		Optional<User> optionalUser = userRepo.findById(id);
		UserServiceImpl userServ=new UserServiceImpl();
		if(optionalUser.isPresent())
		{
		userRepo.deleteById(id);
		return userServ.userResponse(ok,"User deleted" , "eyJhbGciOiJIUzI1NiIsInR5g6");
		}
		else {

		return userServ.userResponse(notOk,"User does not exist" , "eyJhbGciOiJIUzI1NiIsInR5g15");
		}
		}

	public User getUserById(long id) {
		Optional<User> findById = userRepo.findById(id);

		if(findById.isPresent())
		{
		return findById.get();
		}
		else
		{
		return null;
		}
		}
	public JSONObject updateUserPassword(UpdatePasswordDto updatePasswordDto, Long id) {
		
			Optional<User> findById = userRepo.findById(id);

         	UserServiceImpl userServ=new UserServiceImpl();
			if(findById.isPresent())
			{
			User user = findById.get();
			String newPassword = updatePasswordDto.getNewPassword();
			String confirmNewPassword = updatePasswordDto.getConfirmNewPassword();
			if(user.getPassword().equals(updatePasswordDto.getOldPassword()))
			{
			if(newPassword.equals(confirmNewPassword))
			{
			if(newPassword.equals(updatePasswordDto.getOldPassword()))
			{
			return userServ.userResponse(ok,"old passwoprd and new password both are same please enter different new password" , "eyJhbGciOiJIUzI1NiIsInR5dCj9...");
			}
			else {
			user.setPassword(newPassword);
			userRepo.save(user);
			return userServ.userResponse(ok,"Password getting updated" , "eyJhbGciOiJIUzI1NiIsInR5k10...");
			}
			}
			else
			{
			return userServ.userResponse(notOk,"New apssword and conformed password both are not match" , "eyJhbGciOiJIUzI1NiIsInR5m12");
			}
			}
			else
			{
			return userServ.userResponse(notOk," Old passsword was in correct Please enter valid old password " , "eyJhbGciOiJIUzI1NiIsInR5cCn13");
			}
			}
			else
			{
			return userServ.userResponse(notOk,"User does not exist plese enter valid user id" , "eyJhbGciOiJIUzI1NiIsIn");
			}
			}






	
}
