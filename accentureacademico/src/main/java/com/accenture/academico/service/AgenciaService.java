package com.accenture.academico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.accenture.academico.model.*;
import com.accenture.academico.repository.AgenciaRepository;
import org.springframework.data.domain.Pageable;
@Service
public class AgenciaService {
	
	@Autowired
	private AgenciaRepository agenciaRepository;
	
	// METODO PARA BUSCAR TODAS AS AGENCIAS
	public List<Agencia> buscarAgencias(Pageable pageable){
		return this.agenciaRepository.findAll(pageable).getContent();
	}
	
	
	//MÉTODO PARA SALVAR AGENCIA
	public void salvarAgencia(Agencia agencia) {
		this.agenciaRepository.save(agencia);
	}
	
	
	//MÉTODO PARA ALTERAR AGENCIA
	public void atualizarAgencia(Agencia agencia, Long id) {
		Agencia agenciaBD = this.agenciaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agencia não encontrada"));
	    BeanUtils.copyProperties(agencia, agenciaBD, "id");
	    this.salvarAgencia(agenciaBD);
	}
	
	
	//MÉTODO PARA EXCLUIR AGENCIA
	public void excluirAgencia(Long id) {
		this.agenciaRepository.deleteById(id);
	
	}
	
	//MÉTODO PARA BUSCAR AGENCIA POR ID
	public Agencia buscarAgenciaID(Long id){
		Optional<Agencia> agencia = agenciaRepository.findById(id);
		return agencia.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agencia não encontrada"));
	}
     
	public List<ContaDigital> listarContas(Long idAgencia){
		
		Agencia agencia;
		agencia = buscarAgenciaID(idAgencia);
		
		return agencia.getContas();
	}
}
