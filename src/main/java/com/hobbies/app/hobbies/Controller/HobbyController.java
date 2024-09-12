package com.hobbies.app.hobbies.Controller;
import com.hobbies.app.hobbies.Models.Hobby;
import com.hobbies.app.hobbies.Models.User;
import com.hobbies.app.hobbies.Repo.HobbyRepo;
import com.hobbies.app.hobbies.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@RestController
@RequestMapping("/hobbies")
public class HobbyController {
	
	@Autowired
	private HobbyRepo hobbyRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	// Add hobby to a person
    @PostMapping("/add/{userId}")
    public ResponseEntity<Hobby> addHobby(@PathVariable Long userId, @RequestBody Hobby hobby) {
    	User user = userRepo.findById(userId).orElse(null);
    	if(user != null) {
    		boolean hobbyExists = user.getHobbies().stream()
                    .anyMatch(h -> h.getName().equals(hobby.getName()));
    		if(!hobbyExists) {
    			Hobby newhobby = new Hobby();
    			newhobby.setName(hobby.getName());
    			newhobby.setUser(user);
        		user.addHobby(newhobby);
        		return ResponseEntity.ok(hobbyRepo.save(newhobby));
    			
    		}
    		
    	}
    	
    	return ResponseEntity.notFound().build();
        
    }
	
	@GetMapping(value = "/gethobbies")
    public List<Hobby> getAllHobbies() {
        return hobbyRepo.findAll();
    }
	
	@GetMapping(value = "/gethobbies/{id}")
	public Hobby getHobbyById(@PathVariable Long id) {
		return hobbyRepo.findById(id).orElse(null);
	}
	
	
	
    @PutMapping(value = "/updatehobbies/{id}")
    public ResponseEntity<Hobby> updateHobby(@PathVariable Long id, @RequestBody Hobby updatedHobby) {
    	Hobby existingHobby = hobbyRepo.findById(id).orElse(null);
        if (existingHobby != null) {
            existingHobby.setName(updatedHobby.getName());
            existingHobby.setUser(updatedHobby.getUser());
            return ResponseEntity.ok(hobbyRepo.save(existingHobby));
        }
        return ResponseEntity.notFound().build();  // Return 404 if hobby not found
    }

    @DeleteMapping(value = "deletehobbies/{id}")
    public void deleteHobby(@PathVariable Long id) {
        hobbyRepo.deleteById(id);
    }

    @GetMapping(value = "/search")
    public List<User> getUsersByHobby(@RequestParam String hobbyName) {
    	List<Hobby> hobbies = hobbyRepo.findByName(hobbyName);
        return hobbies.stream()
                      .map(Hobby::getUser)  // Extract the person from each hobby
                      .distinct()              // To avoid duplicates if a person has the same hobby multiple times
                      .collect(Collectors.toList());
    }
}
	
	
	
	
	
