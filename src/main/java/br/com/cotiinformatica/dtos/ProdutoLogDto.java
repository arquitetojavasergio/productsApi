package br.com.cotiinformatica.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class ProdutoLogDto {

	private UUID id;
	private LocalDateTime dataHora;
	private TipoAcao tipoAcao;
	private ProdutoResponseDto produto;
	
	public enum TipoAcao {
		INSERT,
		UPDATE,
		DELETE
	}
}
