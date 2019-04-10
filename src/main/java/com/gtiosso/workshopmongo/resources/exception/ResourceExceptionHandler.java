package com.gtiosso.workshopmongo.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gtiosso.workshopmongo.services.exception.ObjectNotFoundException;

// Indica que a classe é responsavel por tratar possiveis erros nas requisições
@ControllerAdvice
public class ResourceExceptionHandler {
	
	// Declarando a Anotação Exception para funcionar
	// e identificar que quando ocorrer essa exceção é para realizar este tratamento
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(
				System.currentTimeMillis(), 
				status.value(), 
				"Not Found", 
				e.getMessage(), 
				request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	

}
