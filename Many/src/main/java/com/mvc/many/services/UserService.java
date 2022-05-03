package com.mvc.many.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.mvc.many.models.User;
import com.mvc.many.models.UserLogin;
import com.mvc.many.repositories.UserRepo;

@Service
public class UserService {
	@Autowired
    private UserRepo repository;
	
	public User register(User newUser, BindingResult result) {
    
    Optional<User> maybeNinja  = repository.findByEmail(newUser.getEmail()); 	
    System.out.println(result);
    if(maybeNinja.isPresent()) {
    	result.rejectValue("email","Matches","Account already exists. Try again.");
    }
    if(!newUser.getPassword().equals(newUser.getConfirm())) {
    	result.rejectValue("confirm", "Matches", "Passwords must match.");
    }
	// Return null if result has errors
	if(result.hasErrors()) {
		System.out.println("There's an error fellow.");
		return null;
	}
    	String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    	newUser.setPassword(hashed);
    	System.out.println("Whattttt.");
    	return repository.save(newUser);
   }
	 public User login(UserLogin newLogin, BindingResult result) {
	    	
	    	Optional<User> maybeUser = repository.findByEmail(newLogin.getEmail());
	        
	    	if(!maybeUser.isPresent()) {
	    		result.rejectValue("email", "Matches", "User not found!");
	    		return null;
	    	}
	    	
	    	User user = maybeUser.get();
	        
	    	if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
	    	    result.rejectValue("password", "Matches", "Invalid Password!");
	    	}
	    	
	    	if(result.hasErrors()) {
	    		return null;
	    	}
	        return user;
	    }
	public User findById(Long id) {
		Optional<User> result = repository.findById(id);
			if(result.isPresent()) {
				System.out.println("The id is present.");
				return result.get();
			}else {
			System.out.println("Well something went wrong.");
			}
		return null;
	}
    public User save(User user) {
        return repository.save(user);
    }
}
