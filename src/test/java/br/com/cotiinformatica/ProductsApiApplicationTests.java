package br.com.cotiinformatica;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import br.com.cotiinformatica.controllers.ProdutoController;
import br.com.cotiinformatica.dtos.ProdutoRequestDto;
import br.com.cotiinformatica.dtos.ProdutoResponseDto;
import br.com.cotiinformatica.handlers.ValidationExceptionHandler;
import br.com.cotiinformatica.services.ProdutoService;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(MockitoExtension.class)
class ProductsApiApplicationTests {
	
	@Autowired MockMvc mockMvc;
	@MockBean ProdutoService produtoService;
	@InjectMocks ProdutoController produtoController;
	
	/*
	 * Método para configurar o MOCK das classes
	 * que serão utilizadas nos testes.
	 * Este método será executado antes de cada teste.
	 */
	@BeforeEach
	public void setUp() {
		
		//configurando o controlador de produtos e os handlers de erros
		mockMvc = MockMvcBuilders
				.standaloneSetup(produtoController) //instanciando o controller
				.setControllerAdvice(new IllegalArgumentException()) //handler de erro
				.setControllerAdvice(new ValidationExceptionHandler()) //handler de erro
				.build();
	}

	@Test
	void cadastrarProdutoTest() throws Exception {	
		
		var faker = new Faker();
		
		//criando os dados da requisição (request) para cadastro do produto
		var request = new ProdutoRequestDto();
		request.setNome(faker.commerce().productName());
		request.setPreco((double) faker.number().numberBetween(100, 1000));
		request.setQuantidade(faker.number().numberBetween(1, 100));
		request.setCategoriaId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
		
		//definindo os dados que a API deverá retornar (response)
		var response = new ProdutoResponseDto();
		response.setNome(request.getNome());
		response.setPreco(request.getPreco());
		response.setQuantidade(request.getQuantidade());
		
		//criando o critério de testes -> quando eu enviar um objeto do tipo
		//ProdutoRequestDto para o dominio cadastrar e ele então deverá retornar
		//um objeto do tipo ProdutoResponseDto
		when(produtoService.cadastrar(any(ProdutoRequestDto.class)))
			.thenReturn(response);

		//enviando a requisição para a API
		mockMvc.perform(post("/api/produtos") //endpoint
					.contentType(MediaType.APPLICATION_JSON) //formato JSON
					.content(new ObjectMapper().writeValueAsString(request))) //enviando os dados
					.andExpect(status().isCreated()) //verificando o retorno 201 (CREATED)
					.andExpect(jsonPath("$.nome").value(response.getNome())) //comparando o nome
					.andExpect(jsonPath("$.preco").value(response.getPreco())) //comparando o preço
					.andExpect(jsonPath("$.quantidade").value(response.getQuantidade())); //comparando a quantidade
	}

	@Test
	void atualizarProdutoTest() throws Exception {
		
		var faker = new Faker();
		var id = UUID.randomUUID();
		
		//criando os dados da requisição (request) para cadastro do produto
		var request = new ProdutoRequestDto();
		request.setNome(faker.commerce().productName());
		request.setPreco((double) faker.number().numberBetween(100, 1000));
		request.setQuantidade(faker.number().numberBetween(1, 100));
		request.setCategoriaId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
		
		//definindo os dados que a API deverá retornar (response)
		var response = new ProdutoResponseDto();
		response.setNome(request.getNome());
		response.setPreco(request.getPreco());
		response.setQuantidade(request.getQuantidade());
		
		//criando o critério de testes -> quando eu enviar um objeto do tipo
		//ProdutoRequestDto para o dominio atualizar e ele então deverá retornar
		//um objeto do tipo ProdutoResponseDto
		when(produtoService.atualizar(eq(id), any(ProdutoRequestDto.class)))
			.thenReturn(response);

		//enviando a requisição para a API
		mockMvc.perform(put("/api/produtos/" + id) //endpoint
					.contentType(MediaType.APPLICATION_JSON) //formato JSON
					.content(new ObjectMapper().writeValueAsString(request))) //enviando os dados
					.andExpect(status().isOk()) //verificando o retorno 200 (OK)
					.andExpect(jsonPath("$.nome").value(response.getNome())) //comparando o nome
					.andExpect(jsonPath("$.preco").value(response.getPreco())) //comparando o preço
					.andExpect(jsonPath("$.quantidade").value(response.getQuantidade())); //comparando a quantidade		
	}

	@Test
	void excluirProdutoTest() throws Exception {
		
		var id = UUID.randomUUID();
		
		var response = new ProdutoResponseDto();
		
		when(produtoService.excluir(eq(id)))
			.thenReturn(response);
		
		mockMvc.perform(delete("/api/produtos/" + id)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());	
	}

	@Test
	void consultarProdutosTest() throws Exception {
		
		var faker = new Faker();
		
		var response1 = new ProdutoResponseDto();
		response1.setNome(faker.commerce().productName());
		response1.setPreco((double) faker.number().numberBetween(100, 1000));
		response1.setQuantidade(faker.number().numberBetween(1, 100));
		
		var response2 = new ProdutoResponseDto();
		response2.setNome(faker.commerce().productName());
		response2.setPreco((double) faker.number().numberBetween(100, 1000));
		response2.setQuantidade(faker.number().numberBetween(1, 100));
		
		//criando uma lista de produtos
		var lista = new ArrayList<ProdutoResponseDto>();
		lista.add(response1);
		lista.add(response2);
		
		//critério para que ao executar uma consulta paginada
		//então uma lista de produto seja devolvida
		when(produtoService.consultar(any(Pageable.class)))
			.thenReturn(lista);
		
		mockMvc.perform(get("/api/produtos") //endpoint de consulta
				.param("page", "0") //parametro: numero da página
				.param("size", "10") //parametro: quantidade de registros
				.param("sortBy", "id") //parametro: tipo de ordenação
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].nome").value(response1.getNome())) 
				.andExpect(jsonPath("$[0].preco").value(response1.getPreco()))
				.andExpect(jsonPath("$[0].quantidade").value(response1.getQuantidade()))
				.andExpect(jsonPath("$[1].nome").value(response2.getNome())) 
				.andExpect(jsonPath("$[1].preco").value(response2.getPreco()))
				.andExpect(jsonPath("$[1].quantidade").value(response2.getQuantidade())); 
	}

	@Test
	void obterProdutoPorIdTest() throws Exception {
		
		var faker = new Faker();
		var id = UUID.randomUUID();
		
		var response = new ProdutoResponseDto();
		response.setNome(faker.commerce().productName());
		response.setPreco((double) faker.number().numberBetween(100, 1000));
		response.setQuantidade(faker.number().numberBetween(1, 100));
		
		when(produtoService.obterPorId(id))
			.thenReturn(response);
		
		mockMvc.perform(get("/api/produtos/" + id) //endpoint de consulta
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nome").value(response.getNome())) 
				.andExpect(jsonPath("$.preco").value(response.getPreco()))
				.andExpect(jsonPath("$.quantidade").value(response.getQuantidade()));
	}
}
