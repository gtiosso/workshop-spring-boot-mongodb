package com.gtiosso.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gtiosso.workshopmongo.domain.User;
import com.gtiosso.workshopmongo.dto.UserDTO;
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
	public ResponseEntity<List<UserDTO>> findAll(){
		// Invocando o metodo findAll do Servico para coletar do banco de dados
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		// Instanciando o ResponseEntity
		// .ok() = metodo que instancia o ResponseEntity com o codigo de resposta de sucesso
		// .body() = define o corpo da resposta
		return ResponseEntity.ok().body(listDto);
	}
	
	// Criando a rota para buscar (GET) usuários expecificos
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	
	// ReponseEntity = Encapsula toda a estrutura necessária
	// para o retorno HTTP Completo (Cabeçalho, body...)
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		
		User obj = service.findById(id);	
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	// Criando a rota para inserir (Post) usuários expecificos
	@RequestMapping(method = RequestMethod.POST)
	// Outra possibilidade = @PostMapping
	
	// ReponseEntity = Encapsula toda a estrutura necessária
	// para o retorno HTTP Completo (Cabeçalho, body...)
	// RequestBody = Necessário para aceitar o recebimento de parametros via body
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){
		
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		
		// Retornando o caminho para acesso do novo usuário
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	// Criando a rota para deletar (DELETE) usuários expecificos
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	
	// ReponseEntity = Encapsula toda a estrutura necessária
	// para o retorno HTTP Completo (Cabeçalho, body...)
	public ResponseEntity<Void> deleteById(@PathVariable String id){
		
		service.delete(id);	
		return ResponseEntity.noContent().build();
	}
}
