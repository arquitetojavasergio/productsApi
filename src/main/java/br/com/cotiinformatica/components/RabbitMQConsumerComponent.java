package br.com.cotiinformatica.components;

import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cotiinformatica.collections.HistoricoProdutos;
import br.com.cotiinformatica.dtos.ProdutoLogDto;
import br.com.cotiinformatica.repositories.HistoricoProdutosRepository;

@Component
public class RabbitMQConsumerComponent {

	@Autowired HistoricoProdutosRepository historicoProdutosRepository;
	@Autowired ObjectMapper objectMapper;
	
	@RabbitListener(queues = { "produtos" })
	public void proccess(@Payload String message) throws Exception {
		
		//deserializar a mensagem contida na fila
		var produto = objectMapper.readValue(message, ProdutoLogDto.class);
		
		//gravar no mongodb
		var historico = new HistoricoProdutos();
		historico.setId(UUID.randomUUID());
		historico.setTipoOperacao(produto.getTipoAcao().toString());
		historico.setDataHoraOperacao(produto.getDataHora());
		historico.setNomeProduto(produto.getProduto().getNome());
		historico.setPrecoProduto(produto.getProduto().getPreco());
		historico.setQuantidadeProduto(produto.getProduto().getQuantidade());
		historico.setCategoriaProduto(produto.getProduto().getCategoria().getNome());
		
		historicoProdutosRepository.save(historico);
	}
}
