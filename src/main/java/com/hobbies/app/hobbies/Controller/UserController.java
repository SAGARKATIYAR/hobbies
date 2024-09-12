package com.hobbies.app.hobbies.Controller;
import com.hobbies.app.hobbies.Models.User;
import com.hobbies.app.hobbies.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepo userRepo;
	
	@PostMapping(value = "/save")
    public User addUser(@RequestBody User user) {
        return userRepo.save(user);
    }
	
	@GetMapping(value = "/users")
	public List<User> getUsers() {
		return userRepo.findAll();
		
	}
	
	// Get person by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepo.findById(id).orElse(null);
    }
	
	
	 // Update a person
    @PutMapping(value = "/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
    	return userRepo.findById(id)
                .map(existingUser -> {
                    existingUser.setName(userDetails.getName());
                    existingUser.setPhoneNumber(userDetails.getPhoneNumber());
                    existingUser.setAddress(userDetails.getAddress());
                    return userRepo.save(existingUser);
                })
                .orElse(null); // Returns null if not found
    }
    
 // Delete a person
    @DeleteMapping(value = "/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepo.deleteById(id);
    }
    


}
