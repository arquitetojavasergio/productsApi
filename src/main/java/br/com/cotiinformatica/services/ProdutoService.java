package br.com.cotiinformatica.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.components.RabbitMQProducerComponent;
import br.com.cotiinformatica.dtos.ProdutoRequestDto;
import br.com.cotiinformatica.dtos.ProdutoResponseDto;
import br.com.cotiinformatica.dtos.ProdutoLogDto.TipoAcao;
import br.com.cotiinformatica.entities.Produto;
import br.com.cotiinformatica.repositories.CategoriaRepository;
import br.com.cotiinformatica.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired ProdutoRepository produtoRepository;
	@Autowired CategoriaRepository categoriaRepository;
	@Autowired ModelMapper modelMapper;
	@Autowired RabbitMQProducerComponent producerComponent;
	
	public ProdutoResponseDto cadastrar(ProdutoRequestDto dto) {

		var categoria = categoriaRepository.findById(dto.getCategoriaId())
				.orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada."));
		
		var produto = modelMapper.map(dto, Produto.class);
		produto.setId(UUID.randomUUID());
		produto.setCategoria(categoria);
		
		produtoRepository.save(produto);
		
		var response = modelMapper.map(produto, ProdutoResponseDto.class);
		
		try {
			producerComponent.createLog(response, TipoAcao.INSERT);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	public ProdutoResponseDto atualizar(UUID id, ProdutoRequestDto dto) {

		var produto = produtoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));
		
		var categoria = categoriaRepository.findById(dto.getCategoriaId())
				.orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada."));
		
		produto.setNome(dto.getNome());
		produto.setPreco(BigDecimal.valueOf(dto.getPreco()));
		produto.setQuantidade(dto.getQuantidade());
		produto.setCategoria(categoria);
		
		produtoRepository.save(produto);
		
		var response = modelMapper.map(produto, ProdutoResponseDto.class);
		
		try {
			producerComponent.createLog(response, TipoAcao.UPDATE);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	public ProdutoResponseDto excluir(UUID id) {

		var produto = produtoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));
		
		produtoRepository.delete(produto);
		
		var response = modelMapper.map(produto, ProdutoResponseDto.class);
		
		try {
			producerComponent.createLog(response, TipoAcao.DELETE);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	public List<ProdutoResponseDto> consultar(Pageable pageable) {

		var produtos = produtoRepository.findAll(pageable);
		
		return produtos
				.stream()
				.map(item -> modelMapper.map(item, ProdutoResponseDto.class))
				.collect(Collectors.toList());
	}
	
	public ProdutoResponseDto obterPorId(UUID id) {

		var produto = produtoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));
		
		return modelMapper.map(produto, ProdutoResponseDto.class);
	}
}
