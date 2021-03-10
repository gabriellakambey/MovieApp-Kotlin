package com.digimaster.backendTraining;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PersonService {
	
	private PersonRepository personRepository;
	private final Path root = Paths.get("uploads");
	
	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	public PersonModel createPerson(PersonModel personModel) {
		return personRepository.save(personModel);
	}
	
	public Optional<PersonModel> getPerson(int personId) {
		return personRepository.findById(personId);
	}
	
	public Iterable<PersonModel> getAllPerson() {
		return personRepository.findAll();
	}
	
	public PersonModel getPersonByEmail(String email) {
		return personRepository.getPersonModelByUserEmail(email);
	}
	
	public boolean deletePerson(int id) {
		Optional<PersonModel> personModel = personRepository.findById(id);
		if(personModel.isPresent()) {
			personRepository.deleteById(id);
			return true;
		}else {
			return false;
		}
	}
	
	public void saveFile(MultipartFile file) {
		try {
			if (!Files.exists(root)) {
				Files.createDirectories(root);
			}
			
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public boolean login(String email, String password) {
		PersonModel personModel = personRepository.getPersonModelByUserEmail(email);
		if(Objects.nonNull(personModel)) {
			if(personModel.getUserPassword().equals(password)) {
				return true;
			}else {
				return false;
			}
		} else {
			return false;
		}
	}

}
