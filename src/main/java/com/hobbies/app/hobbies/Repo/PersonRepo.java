package com.hobbies.app.hobbies.Repo;
import com.hobbies.app.hobbies.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepo extends JpaRepository<Person, Long>{

}
