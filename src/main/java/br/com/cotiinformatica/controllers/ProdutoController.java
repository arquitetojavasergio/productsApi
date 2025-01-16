package br.com.cotiinformatica.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.ProdutoRequestDto;
import br.com.cotiinformatica.dtos.ProdutoResponseDto;
import br.com.cotiinformatica.services.ProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired ProdutoService produtoService;
	
	@PostMapping
	public ResponseEntity<ProdutoResponseDto> post(@RequestBody @Valid ProdutoRequestDto dto) {
		var result = produtoService.cadastrar(dto);
		return ResponseEntity.status(201).body(result);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ProdutoResponseDto> put(@PathVariable UUID id, @RequestBody @Valid ProdutoRequestDto dto) {
		var result = produtoService.atualizar(id, dto);		
		return ResponseEntity.status(200).body(result);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ProdutoResponseDto> delete(@PathVariable UUID id) {
		var result = produtoService.excluir(id);
		return ResponseEntity.status(200).body(result);
	}
	
	@GetMapping()
	public ResponseEntity<List<ProdutoResponseDto>> getAll(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy
			) {
		var pageable = PageRequest.of(page, size, Sort.by(sortBy));		
		var result = produtoService.consultar(pageable);		
		return ResponseEntity.status(200).body(result);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ProdutoResponseDto> getById(@PathVariable UUID id) {
		var result = produtoService.obterPorId(id);
		return ResponseEntity.status(200).body(result);
	}
}










