package com.accenture.academico.controller;

import java.util.List;

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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import com.accenture.academico.model.Cliente;
import com.accenture.academico.service.ClienteService;

@RestController //recebe requisições restful
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	//MÉTODO PARA BUSCAR TODOS OS CLIENTES
	@GetMapping("/")
	public List<Cliente> buscarClientes(Pageable pageable){
		return this.clienteService.buscarClientes(pageable);
	}
	
	
	//MÉTODO PARA SALVA CLIENTE
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public void salvarCliente(@RequestBody Cliente cliente) {
		this.clienteService.salvarCliente(cliente);
	}
	
	
	//MÉTODO PARA ATUALIZAR CLIENTE
	@PutMapping("/{id}")
	public void atualizarCliente(@PathVariable("id")Long id, @RequestBody Cliente cliente) {
		this.clienteService.atualizarCliente(cliente, id);
	}

	
	//MÉTODO PARA EXCLUIR CLIENTE
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String excluirCliente(@PathVariable("id") Long id) {
		this.clienteService.excluirCliente(id);
		
		return "Cliente de ID " +id+ " foi excluido com sucesso!";
	}
	
	
	//MÉTODO PARA BUSCAR CLIENTES POR ID
	@GetMapping("/{id}")
	public Cliente buscarAgenciaID(@PathVariable("id")Long id) {
		return this.clienteService.buscarClienteID(id);
	}
}
