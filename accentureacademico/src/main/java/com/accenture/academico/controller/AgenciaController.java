package com.accenture.academico.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;
import com.accenture.academico.model.Agencia;
import com.accenture.academico.repository.AgenciaRepository;
import com.accenture.academico.service.AgenciaService;


@RestController //recebe requisições restful
@RequestMapping(value = "/agencias")
public class AgenciaController {
	
	@Autowired
	private AgenciaService agenciaService;
	
	
	//METODO PARA BUSCAR TODAS AS AGENCIAS
	@GetMapping("/")
    public List<Agencia> buscarAgencias(Pageable pageable){
		return this.agenciaService.buscarAgencias(pageable);
	}
	
	
	//MÉTODO PARA SALVAR AGENCIA
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public void salvarAgencia(@RequestBody Agencia agencia) {
		this.agenciaService.salvarAgencia(agencia);
	}
		
	
	//MÉTODO PARA ALTERAR AGENCIA
	@PutMapping("/{id}")
	public void alterarAgencia(@PathVariable("id") Long id, @RequestBody Agencia agencia) {
		this.agenciaService.atualizarAgencia(agencia, id);
	}
	
	//MÉTODO PARA DELETAR AGENCIA
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String excluirAgencia(@PathVariable("id") Long id) {
		
		this.agenciaService.excluirAgencia(id);
		
		return "Agencia de ID " + id + " foi deletada com sucesso!";
				
	}
	
	//MÉTODO PARA BUSCAR AGENCIA POR ID
	@GetMapping("/{id}")
	public Agencia buscarAgenciaID(@PathVariable("id")Long id){
		return this.agenciaService.buscarAgenciaID(id);
	}
		
		

}
