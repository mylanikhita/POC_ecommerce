package com.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.LoginDto;
import com.ecommerce.dto.UpdatePasswordDto;
import com.ecommerce.dto.UpdateUserDetailesDto;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.UserServiceImpl;


@RestController
@RequestMapping("/userss")
public class UserRestController {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserServiceImpl userServ;

//Register
	@PostMapping("/Register")
	public JSONObject saveUser(@RequestBody User user)
	{
	User save = userRepo.save(user);
	if(save!=null)
	{
	JSONObject response = userServ.userResponse("ok","Registration was succesfull" , "eyJhbGciOiJIUzI1NiIsInR5aB...");
	return response;
	}
	else
	{
	JSONObject response = userServ.userResponse("Not ok","Registration was failed" , "eyJhbGciOiJIUzI1NiIsInR5aC...");
	return response;
	}

	}
	
//GetUsersList
	@GetMapping("/getUsers")
	public List<User> getUsers()
	{
	List<User> users = userRepo.findAll();
	return users;
	}
	
//VerifyLogin
	@PostMapping("/verifyLogin")
	public JSONObject verifylogin(@RequestBody LoginDto users)
	{
	String email = users.getEmail();
	String password = users.getPassword();
	User user = userRepo.findByEmail(email);
	if(user!=null) {
	if(user.getEmail().equals(email)&& user.getPassword().equals(password))
	{
	JSONObject response = userServ.userResponse("ok","Log In was succesfull" , "eyJhbGciOiJIUzI1NiIsInR5cC...");
	return response;
	}

	else
	{
	JSONObject response = userServ.userResponse("Not ok","Please enter valid user name and password" , "eyJhbGciOiJIUzI1NiIsInR5cC1...");
	return response;
	}
	}
	else
	{
	JSONObject response = userServ.userResponse("Not ok","User does not exists Please enter valid user name" , "eyJhbGciOiJIUzI1NiIsInR5cC2...");
	return response;
	}
	}
	
//DeleteUser
	@DeleteMapping(value="/deleteUser/{id}")
	public JSONObject delateUser(@PathVariable Long id)
	{
	Optional<User> findById = userRepo.findById(id);
	User user = findById.get();
	if(user!=null)
	{
	userRepo.deleteById(id);
	JSONObject response = userServ.userResponse("ok","User record getting deleted" , "eyJhbGciOiJIUzI1NiIsInR5cC2...");
	return response;
	}
	else
	{
	JSONObject response = userServ.userResponse("Not ok","Unable to delete Please enter valid user Id" , "eyJhbGciOiJIUzI1NiIsInR5cC2...");
	return response;
	}

	}
//Updatebyid
	@PutMapping(value="updateUser/{id}")
	public JSONObject updateUser(@RequestBody UpdateUserDetailesDto user ,@PathVariable Long id)
	{
	Optional<User> findById = userRepo.findById(id);
	User user2 = findById.get();
	String password = user2.getPassword();
	User users=new User();
	String firstName = user.getFirstName();
	String lastName = user.getLastName();
	String email = user.getEmail();
	users.setId(id);
	users.setFirstName(firstName);
	users.setLastName(lastName);
	users.setEmail(email);
	users.setPassword(password);
	User saveUser = userRepo.save(users);
	if(saveUser!=null)
	{
	JSONObject response = userServ.userResponse("ok","User Details are Getting Updated" , "eyJhbGciOiJIUzI1NiIsInR5dC1...");
	return response;
	}
	else
	{
	JSONObject response = userServ.userResponse("Not ok","User Details are Not Getting Updated" , "eyJhbGciOiJIUzI1NiIsInR5dC2s...");
	return response;
	}
	}
//Update by password
	@PutMapping(value="updateUserPassword/{id}")
	public JSONObject updateUserPassword(@RequestBody UpdatePasswordDto updatePasswordDto ,@PathVariable Long id)
	{
	User users=new User();
	String newPassword = updatePasswordDto.getNewPassword();
	String confirmNewPassword = updatePasswordDto.getConfirmNewPassword();
	Optional<User> findById = userRepo.findById(id);
	User user = findById.get();
	String firstName = user.getFirstName();
	String lastName = user.getLastName();
	String email = user.getEmail();
	if(user.getPassword().equals(updatePasswordDto.getOldPassword()))
	{
	if(newPassword.equals(confirmNewPassword))
	{
	users.setId(id);
	users.setFirstName(firstName);
	users.setLastName(lastName);
	users.setEmail(email);
	users.setPassword(newPassword);
	User save = userRepo.save(users);
	if(save!=null)
	{
	JSONObject response = userServ.userResponse("ok","Password getting updated" , "eyJhbGciOiJIUzI1NiIsInR5dC1...");
	return response;
	}
	else
	{
	JSONObject response = userServ.userResponse("Not ok","Password not getting updated" , "eyJhbGciOiJIUzI1NiIsInR5dC2s...");
	return response;
	}

	}
	else
	{
	JSONObject response = userServ.userResponse("Not ok","New password and conformed password both are not match" , "eyJhbGciOiJIUzI1NiIsInR5dC2s...");
	return response;
	}
	}

	else
	{
	JSONObject response = userServ.userResponse("Not ok"," Old password was in correct Please enter valid old password " , "eyJhbGciOiJIUzI1NiIsInR5cC2...");
	return response;
	}
	
	}

	
}
