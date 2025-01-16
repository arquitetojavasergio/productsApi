package br.com.cotiinformatica.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.dtos.CategoriaResponseDto;
import br.com.cotiinformatica.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired CategoriaRepository categoriaRepository;
	@Autowired ModelMapper modelMapper;
	
	public List<CategoriaResponseDto> consultar() {

		var categorias = categoriaRepository.findAll();
		
		return categorias
				.stream()
				.map(item -> modelMapper.map(item, CategoriaResponseDto.class))
				.collect(Collectors.toList());		
	}
}
