package br.com.cotiinformatica.dtos;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProdutoRequestDto {

	@NotBlank(message = "Por favor, informe o nome do produto.")
	@Size(min = 8, max = 150, message = "Por favor, informe o nome de 8 a 150 caracteres.")
	private String nome;
	
	@NotNull(message = "Por favor, informe o preço do produto.")
	@Positive(message = "Por favor, informe o preço maior do que zero.")
	private Double preco;
	
	@NotNull(message = "Por favor, informe a quantidade do produto.")
	@Min(value = 1, message = "Por favor, informe a quantidade maior ou igual a 1.")
	private Integer quantidade;
	
	@NotNull(message = "Por favor, informe o ID da categoria.")
	private UUID categoriaId;
}
