package br.com.cotiinformatica.collections;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Data;

@Data
@Document(collection = "historico_produtos")
public class HistoricoProdutos {

	@Id
	private UUID id;
	private LocalDateTime dataHoraOperacao;
	private String tipoOperacao;	
	private String nomeProduto;
	private Double precoProduto;
	private Integer quantidadeProduto;
	private String categoriaProduto;
}
