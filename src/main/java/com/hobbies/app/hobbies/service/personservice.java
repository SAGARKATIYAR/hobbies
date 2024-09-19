package com.hobbies.app.hobbies.service;
import com.hobbies.app.hobbies.Models.Person;
import com.hobbies.app.hobbies.Models.Hobby;

import com.hobbies.app.hobbies.Repo.HobbyRepo;
import com.hobbies.app.hobbies.Repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class personservice {
	@Autowired
    private PersonRepo personRepo;
    
    @Autowired
    private HobbyRepo hobbyRepo;

    // Add a new person
    public Person addPerson(Person person) {
        return personRepo.save(person);
    }

    // Retrieve all persons
    public List<Person> getAllPersons() {
        return personRepo.findAll();
    }
    
 // Get a person by ID
    public Person getPersonById(Long id) {
        return personRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found with ID: " + id));
    }
    
 // Update a person
    public Person updatePerson(Long id, Person updatedPerson) {
        return personRepo.findById(id).map(person -> {
            person.setName(updatedPerson.getName());
            person.setPhoneNumber(updatedPerson.getPhoneNumber());
            person.setAddress(updatedPerson.getAddress());
            return personRepo.save(person);
        }).orElseThrow(() -> new RuntimeException("Person not found"));
    }

    // Delete a person
    public void deletePerson(Long id) {
        personRepo.deleteById(id);
    }
    
 // Add a new hobby to a person
    public Hobby addHobby(Long personId, Hobby hobby) {
        return personRepo.findById(personId).map(person -> {
            hobby.setPerson(person);
            return hobbyRepo.save(hobby);
        }).orElseThrow(() -> new RuntimeException("Person not found"));
    }
    
 // List all hobbies of a person
    public List<Hobby> getHobbiesByPerson(Long personId) {
        return personRepo.findById(personId)
                .map(Person::getHobbies)
                .orElseThrow(() -> new RuntimeException("Person not found"));
    }
    
 // Update a hobby for a person
    public Hobby updateHobby(Long hobbyId, Hobby updatedHobby) {
        return hobbyRepo.findById(hobbyId).map(hobby -> {
            hobby.setName(updatedHobby.getName());
            return hobbyRepo.save(hobby);
        }).orElseThrow(() -> new RuntimeException("Hobby not found"));
    }
    
 // Delete a hobby
    public void deleteHobby(Long hobbyId) {
        hobbyRepo.deleteById(hobbyId);
    }

    // Find persons by hobby
    public List<Person> findPersonsByHobby(String hobbyName) {
        List<Hobby> hobbies = hobbyRepo.findByName(hobbyName);
        return hobbies.stream()
                      .map(Hobby::getPerson)
                      .distinct()
                      .collect(Collectors.toList());
    }

}


