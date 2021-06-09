package com.accenture.academico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.accenture.academico.model.ContaDigital;
import com.accenture.academico.repository.ContaDigitalRepository;

@Service
public class ContaDigitalService {
	
	@Autowired
	private ContaDigitalRepository contaDigitalRepository;
	
	//MÉTODO PARA BUSCAR TODAS AS CONTAS DIGITAL
	public List<ContaDigital> buscarContaDigital(Pageable pageable){
		return this.contaDigitalRepository.findAll(pageable).getContent();
	}
	
	//MÉTODO PARA SALVAR CONTA DIGITAL
	public void salvarContaDigital(ContaDigital contaDigital) {
		this.contaDigitalRepository.save(contaDigital);
	}
	
	//MÉTODO PARA ALTERAR CONTA DIGITAL
	public void alterarContaDigital(ContaDigital contaDigital, Long id) {
		ContaDigital conta = this.contaDigitalRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agencia não encontrada"));
	    BeanUtils.copyProperties(contaDigital, conta, "id");
	    this.salvarContaDigital(conta);
	}
	
	//MÉTODO PARA EXCLUIR CONTA DIGITAL
	public void excluirContaDigital(Long id) {
		this.contaDigitalRepository.deleteById(id);
	}
	
	//MÉTODO PARA BUSCAR CONTA DIGITAL POR ID
	public ContaDigital buscarContaDigitalID(Long id) {
		Optional<ContaDigital> conta = contaDigitalRepository.findById(id);
		return conta.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Conta Digital não encontrada!"));
	}

}
