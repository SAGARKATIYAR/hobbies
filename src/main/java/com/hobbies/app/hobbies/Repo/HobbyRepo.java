package com.hobbies.app.hobbies.Repo;
import com.hobbies.app.hobbies.Models.Hobby;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepo extends JpaRepository<Hobby, Long>{
	List<Hobby> findByName(String name);
	
}
