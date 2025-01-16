package br.com.cotiinformatica.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.CategoriaResponseDto;
import br.com.cotiinformatica.services.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

	@Autowired CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<CategoriaResponseDto>> getAll() {
		
		var result = categoriaService.consultar();
		//TODO
		return ResponseEntity.status(200).body(result);
	}
}
