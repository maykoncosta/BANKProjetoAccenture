package com.accenture.academico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import com.accenture.academico.model.ContaDigital;
import com.accenture.academico.service.ContaDigitalService;

@RestController //recebe requisições restful
@RequestMapping(value = "/contacorrente")
public class ContaCorrenteController {
	
	@Autowired
	private ContaDigitalService contaCorrenteService;
	
	//MÉTODO PARA BUSCAR TODAS AS CONTAS CORRENTES
	@GetMapping("/")
	public List<ContaDigital> buscarContaCorrente(Pageable pageable){
		return this.contaCorrenteService.buscarContaCorrente(pageable);
	}
	
	
	//MÉTODO PARA SALVAR CONTAS CORRENTES
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public void salvarContaCorrente(@RequestBody ContaDigital contaCorrente) {
		this.contaCorrenteService.salvarContaCorrente(contaCorrente);
	}
	
	
	//MÉTODO PARA ALTERAR CONTA CORRENTE
	@PutMapping("/{id}")
	public void alterarContaCorrente(@PathVariable("id") Long id, @RequestBody ContaDigital contaCorrente) {
		this.contaCorrenteService.alterarContaCorrente(contaCorrente, id);
	}
	
	
	//MÉTODO PARA DELETAR CONTA CORRENTE
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String excluirContaCorrente(@PathVariable("id") Long id) {
		this.contaCorrenteService.excluirContaCorrente(id);
		
		return "Conta corrente de ID " +id+ " foi deletada com sucesso";
	}
	
	
	//MÉTODO PARA BUSCAR AGENCIA POR ID
	@GetMapping("/{id}")
	public ContaDigital buscarContaCorrenteID(@PathVariable("id") Long id) {
		return this.contaCorrenteService.buscarContaCorrenteID(id);
	}

}
