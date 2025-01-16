package br.com.cotiinformatica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.services.RelatorioService;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

	@Autowired RelatorioService relatorioService;
	
	@GetMapping
	public ResponseEntity<String>  get() {
		relatorioService.enviarRelatorio();
		return  ResponseEntity.status(200).body("Relatório de histórico de produtos gerado com sucesso.");
	}
}
