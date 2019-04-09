package com.gtiosso.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gtiosso.workshopmongo.domain.User;


@RestController
@RequestMapping(value = "/users")

public class UserResource {
	@RequestMapping(method = RequestMethod.GET)
	// Outra possibilidade = @GetMapping
	
	// ReponseEntity = Encapsula toda a estrutura necessária
	// para o retorno HTTP Completo (Cabeçalho, body...)
	public ResponseEntity<List<User>> findAll(){
		User maria = new User("1", "Maria Brown", "maria@gmaiil.com");
		User alex = new User("2", "Alex Green", "alex@gmaiil.com");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(maria, alex));
		
		// Instanciando o ResponseEntity
		// .ok() = metodo que instancia o ResponseEntity com o codigo de resposta de sucesso
		// .body() = define o corpo da resposta
		return ResponseEntity.ok().body(list);
	}

}
