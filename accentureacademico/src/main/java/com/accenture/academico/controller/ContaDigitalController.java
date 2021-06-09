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
import org.springframework.security.access.prepost.PreAuthorize;

import com.accenture.academico.model.ContaDigital;
import com.accenture.academico.service.ContaDigitalService;

@RestController //recebe requisições restful
@RequestMapping(value = "/contaDigital")
public class ContaDigitalController {
	
	@Autowired
	private ContaDigitalService contaDigitalService;
	
	//MÉTODO PARA BUSCAR TODAS AS CONTAS DIGITAL
	@GetMapping("/")
	@PreAuthorize("hasRole('ANALISTA')")
	public List<ContaDigital> buscarContaDigital(Pageable pageable){
		return this.contaDigitalService.buscarContaDigital(pageable);
	}
	
	
	//MÉTODO PARA SALVAR CONTAS DIGITAL
	@PostMapping("/")
	@PreAuthorize("hasRole('ANALISTA')")
	@ResponseStatus(HttpStatus.CREATED)
	public void salvarcontaDigital(@RequestBody ContaDigital contaDigital) {
		this.contaDigitalService.salvarContaDigital(contaDigital);
	}
	
	
	//MÉTODO PARA ALTERAR CONTA DIGITAL
	@PutMapping("/{id}")
	public void alterarcontaDigital(@PathVariable("id") Long id, @RequestBody ContaDigital contaDigital) {
		this.contaDigitalService.alterarContaDigital(contaDigital, id);
	}
	
	
	//MÉTODO PARA DELETAR CONTA DIGITAL
	@DeleteMapping(value = "/{id}", produces = "application/text")
	@PreAuthorize("hasRole('ANALISTA')")
	public String excluircontaDigital(@PathVariable("id") Long id) {
		this.contaDigitalService.excluirContaDigital(id);
		
		return "Conta corrente de ID " +id+ " foi deletada com sucesso";
	}
	
	
	//MÉTODO PARA BUSCAR CONTADIGITAL POR ID
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ANALISTA')")
	public ContaDigital buscarcontaDigitalID(@PathVariable("id") Long id) {
		return this.contaDigitalService.buscarContaDigitalID(id);
	}

}
