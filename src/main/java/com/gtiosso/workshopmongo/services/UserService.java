package com.gtiosso.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtiosso.workshopmongo.domain.User;
import com.gtiosso.workshopmongo.repository.UserRepository;

@Service
public class UserService {

	// Autowired = Instancia automatiamente a Interface subsequente
	// Injeção de dependencia automatica
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
}
