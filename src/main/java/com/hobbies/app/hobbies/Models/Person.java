package com.hobbies.app.hobbies.Models;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String phoneNumber;
    private String address;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "person")
	@JsonManagedReference
	private List<Hobby> hobbies = new ArrayList<>();
	
	public Person() {}
	
	public Person(String name, String phoneNumber, String address, List<Hobby> hobbies) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.hobbies = hobbies;
	}
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Hobby> getHobbies() {
		return hobbies;
	}
	public void setHobbies(List<Hobby> hobbies) {
		this.hobbies = hobbies;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String number) {
		this.phoneNumber = number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}