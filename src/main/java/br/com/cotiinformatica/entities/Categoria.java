package br.com.cotiinformatica.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Categoria {

	@Id
	private UUID id;
	
	@Column(length = 50, nullable = false)
	private String nome;
	
	@OneToMany(mappedBy = "categoria")
	private List<Produto> produtos;
}
