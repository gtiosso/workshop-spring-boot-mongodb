package com.gtiosso.workshopmongo.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gtiosso.workshopmongo.domain.User;
import com.gtiosso.workshopmongo.services.UserService;


@RestController
@RequestMapping(value = "/users")

public class UserResource {
	// Injetando automaticament a dependencia do Serviço UserService
	@Autowired
	private UserService service;
	
	
	@RequestMapping(method = RequestMethod.GET)
	// Outra possibilidade = @GetMapping
	
	// ReponseEntity = Encapsula toda a estrutura necessária
	// para o retorno HTTP Completo (Cabeçalho, body...)
	public ResponseEntity<List<User>> findAll(){
		// Invocando o metodo findAll do Servico para coletar do banco de dados
		List<User> list = service.findAll();
		
		// Instanciando o ResponseEntity
		// .ok() = metodo que instancia o ResponseEntity com o codigo de resposta de sucesso
		// .body() = define o corpo da resposta
		return ResponseEntity.ok().body(list);
	}

}
