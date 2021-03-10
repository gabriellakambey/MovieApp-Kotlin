package com.digimaster.backendTraining;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonModel, Integer> {
	
	PersonModel getPersonModelByUserEmail(String userEmail);

}
