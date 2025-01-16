package br.com.cotiinformatica.configurations;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

	/*
	 * Lendo a configuração criada no arquivo
	 * application.properties do projeto
	 */
	@Value("${queue.produtos}")
	private String queueProdutos;
	
	/*
	 * Método para retornar a conexão com a fila
	 */
	@Bean
	Queue queue() {
		return new Queue(queueProdutos, true);
	}
}
