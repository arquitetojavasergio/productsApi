package br.com.cotiinformatica.dtos;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ErrorResponseDto {

	private String message;
	private int status;
	private Date createdAt;
	private List<String> errors;
}
