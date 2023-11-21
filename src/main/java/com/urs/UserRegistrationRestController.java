package com.urs;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urs.dto.UserDTO;
import com.urs.error.CustomError;
import com.urs.repository.UserJpaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserRegistrationRestController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserRegistrationRestController.class);
	
	private UserJpaRepository userJpaRepository; 
	
	@Autowired
	public void setUserJpaRepository(UserJpaRepository userJpaRepository) {
		this.userJpaRepository = userJpaRepository;
	}
	
	@GetMapping("/clear")
	public ResponseEntity<UserDTO> clearAll() {
		userJpaRepository.deleteAll();
		
		return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> listAllUsers() {
		List<UserDTO> users = userJpaRepository.findAll();
		
		if (users.isEmpty()) {
			return new ResponseEntity<List<UserDTO>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@PostMapping(value="/", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody final UserDTO user) {
		if (userJpaRepository.findByName(user.getName()) != null) {
			return new ResponseEntity<UserDTO>(new CustomError("User with name " + user.getName() + " already exists"), HttpStatus.CONFLICT);
		}
		
		userJpaRepository.save(user);
		return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
			
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") final long id) {
		Optional<UserDTO> user = userJpaRepository.findById(id);
		
		if (! user.isPresent() ) {
			return new ResponseEntity<UserDTO>(new CustomError("User with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<UserDTO>(user.get(), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable("id") final long id, @Valid @RequestBody final UserDTO user) {
		Optional<UserDTO> persistedUser = userJpaRepository.findById(id);
		
		if (! persistedUser.isPresent()) {
			return new ResponseEntity<UserDTO>(new CustomError("Unable to update. User with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		
		persistedUser.get().setName(user.getName());
		persistedUser.get().setAddress(user.getAddress());
		persistedUser.get().setEmail(user.getEmail());
		
		userJpaRepository.save(persistedUser.get());
		
		return new ResponseEntity<UserDTO>(persistedUser.get(), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") final long id) {
		Optional<UserDTO> user = userJpaRepository.findById(id);
		
		if (! user.isPresent() ) {
			return new ResponseEntity<UserDTO>(new CustomError("Unable to delete. User with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		
		userJpaRepository.deleteById(id);

		return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
		
	}
}
