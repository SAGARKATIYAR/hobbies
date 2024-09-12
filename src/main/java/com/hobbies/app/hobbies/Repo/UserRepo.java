package com.hobbies.app.hobbies.Repo;
import com.hobbies.app.hobbies.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Long> {

}
