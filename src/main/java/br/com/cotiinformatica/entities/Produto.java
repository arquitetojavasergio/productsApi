package br.com.cotiinformatica.entities;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Produto {

	@Id
	private UUID id;
	
	@Column(length = 150, nullable = false)
	private String nome;
	
	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal preco;
	
	@Column(nullable = false)
	private Integer quantidade;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	private Categoria categoria;
}
