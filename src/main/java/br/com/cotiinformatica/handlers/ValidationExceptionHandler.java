package br.com.cotiinformatica.handlers;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.cotiinformatica.dtos.ErrorResponseDto;

@ControllerAdvice
public class ValidationExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponseDto errorHandler(MethodArgumentNotValidException e) {
		
		var errors = new ArrayList<String>();
		
		for(var error : e.getBindingResult().getFieldErrors())
			errors.add(error.getField() + " : " + error.getDefaultMessage());
		
		for(var error : e.getBindingResult().getGlobalErrors())
			errors.add(error.getObjectName() + " : " + error.getDefaultMessage());
		
		var response = new ErrorResponseDto();
		
		response.setMessage("Ocorreram erros de validação para o envio dos dados.");
		response.setStatus(400);
		response.setCreatedAt(new Date());		
		response.setErrors(errors);
		
		return response;
	}	
}
