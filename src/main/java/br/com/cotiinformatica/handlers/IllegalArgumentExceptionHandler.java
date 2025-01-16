package br.com.cotiinformatica.handlers;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.cotiinformatica.dtos.ErrorResponseDto;

@ControllerAdvice
public class IllegalArgumentExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponseDto errorHandler(IllegalArgumentException e) {
		
		var response = new ErrorResponseDto();
		
		response.setMessage("Dados inválidos para esta requisição.");
		response.setStatus(400);
		response.setCreatedAt(new Date());
		
		response.setErrors(new ArrayList<String>());
		response.getErrors().add(e.getMessage());
		
		return response;
	}
}
