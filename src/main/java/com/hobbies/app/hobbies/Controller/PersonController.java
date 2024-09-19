package com.hobbies.app.hobbies.Controller;
import com.hobbies.app.hobbies.Models.Person;
import com.hobbies.app.hobbies.Models.Hobby;
import com.hobbies.app.hobbies.Repo.PersonRepo;
import com.hobbies.app.hobbies.service.personservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private personservice personService;
	
	@PostMapping(value = "/save")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
		return ResponseEntity.ok(personService.addPerson(person));
    }
	
	@GetMapping(value = "/persons")
	public ResponseEntity<List<Person>> getPersons() {
		return ResponseEntity.ok(personService.getAllPersons());
		
	}
	
	// Get person by ID
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
    	return ResponseEntity.ok(personService.getPersonById(id));
    }
	
	
	 // Update a person
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person personDetails) {
    	return ResponseEntity.ok(personService.updatePerson(id, personDetails));
    }
    
 // Delete a person
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
 // Add a hobby to a person
    @PostMapping("/{personId}/hobbies")
    public ResponseEntity<Hobby> addHobby(@PathVariable Long personId, @RequestBody Hobby hobby) {
        return ResponseEntity.ok(personService.addHobby(personId, hobby));
    }
    
 // List all hobbies of a person
    @GetMapping("/{personId}/hobbies")
    public ResponseEntity<List<Hobby>> getHobbiesByPerson(@PathVariable Long personId) {
        return ResponseEntity.ok(personService.getHobbiesByPerson(personId));
    }
    
 // Update a hobby of a person
    @PutMapping("/hobbies/{hobbyId}")
    public ResponseEntity<Hobby> updateHobby(@PathVariable Long hobbyId, @RequestBody Hobby updatedHobby) {
        return ResponseEntity.ok(personService.updateHobby(hobbyId, updatedHobby));
    }
    
 // Delete a hobby
    @DeleteMapping("/hobbies/{hobbyId}")
    public ResponseEntity<Void> deleteHobby(@PathVariable Long hobbyId) {
        personService.deleteHobby(hobbyId);
        return ResponseEntity.noContent().build();
    }
    
 // Find persons by hobby
    @GetMapping("/hobbies/{hobbyName}")
    public ResponseEntity<List<Person>> findPersonsByHobby(@PathVariable String hobbyName) {
    	return ResponseEntity.ok(personService.findPersonsByHobby(hobbyName));
    }


}
