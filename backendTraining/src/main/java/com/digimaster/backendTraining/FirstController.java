package com.digimaster.backendTraining;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
	
	@Autowired
	private PersonService personService;
		

	@PostMapping("/register")
	public GetPersonModel createPerson(@RequestBody PersonModel values) {
		GetPersonModel getPerson = new GetPersonModel();
		PersonModel personModel = personService.createPerson(values);
		if (Objects.nonNull(personModel)) {
			getPerson.setStatus("success");
			getPerson.setPersonModel(personModel);
			return getPerson;
		}else {
			getPerson.setStatus("failed");
			return getPerson;
		}
	}
	
	
	@GetMapping("/person/{email}")
	public PersonModel getPersonByEmail(@PathVariable String email) {
		return personService.getPersonByEmail(email);
	}
	
	@GetMapping("/login")
	public GetPersonModel login(@RequestParam String email, @RequestParam String password) {
		GetPersonModel getPerson = new GetPersonModel();
		if(personService.login(email, password)) {
			getPerson.setStatus("success");
			getPerson.setPersonModel(getPersonByEmail(email));
			return getPerson;
		} else {
			getPerson.setStatus("failed");
			return getPerson;
		}
	}
	
	// Get all user
		@GetMapping("/person/all")
		public Iterable<PersonModel> getAllPerson(){
			return personService.getAllPerson();
		}
		
	
	// Get user by id
	@GetMapping("/person/id/{id}")
	public PersonModel getPerson(@PathVariable int id) {
		return personService.getPerson(id).get();
	}
	
	// Delete user by id
	@DeleteMapping("/person/delete/{id}")
	public String deletePerson(@PathVariable int id) {
		if(personService.deletePerson(id)) {
			return "Success delete person with id "+id;
		}else {
			return "Person with id "+id+" not found";
		}
	}

}
