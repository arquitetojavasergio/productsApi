package br.com.cotiinformatica.components;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cotiinformatica.dtos.ProdutoResponseDto;
import br.com.cotiinformatica.dtos.ProdutoLogDto;
import br.com.cotiinformatica.dtos.ProdutoLogDto.TipoAcao;

@Component
public class RabbitMQProducerComponent {

	@Autowired RabbitTemplate rabbitTemplate;
	@Autowired ObjectMapper objectMapper;
	@Autowired Queue queue;
	
	public void createLog(ProdutoResponseDto dto, TipoAcao tipoAcao) throws Exception {
		
		var produtoLog = new ProdutoLogDto();
		
		produtoLog.setId(UUID.randomUUID());
		produtoLog.setDataHora(LocalDateTime.now());
		produtoLog.setTipoAcao(tipoAcao);
		produtoLog.setProduto(dto);
		
		var json = objectMapper.writeValueAsString(produtoLog);
		
		rabbitTemplate.convertAndSend(queue.getName(), json);
	}
}
