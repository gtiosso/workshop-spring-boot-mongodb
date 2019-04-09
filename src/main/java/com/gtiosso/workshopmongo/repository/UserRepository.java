package com.gtiosso.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gtiosso.workshopmongo.domain.User;

@Repository
// MongoRepository<Tipo da Classe, Tipo do ID>
// Classe para realizar operações no banco de dados
// Todas as operações já estão prédefinidas nesta classe
public interface UserRepository extends MongoRepository<User, String> {

	
}
