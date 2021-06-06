package com.accenture.academico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import com.accenture.academico.model.Cliente;
import com.accenture.academico.repository.AgenciaRepository;
import com.accenture.academico.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private AgenciaRepository agenciaRepository;
	
	//MÉTODO PARA BUSCAR OS CLIENTES
	public List<Cliente> buscarClientes(Pageable pageable){
		return this.clienteRepository.findAll(pageable).getContent();
	}
	
	
	//MÉTODO PARA SALVA CLIENTE
	public Cliente salvarCliente(Cliente cliente) {
		String cpf = cliente.getClienteCPF();
		String nome = cliente.getClienteNome();
		Integer fone = cliente.getClienteFone();
		
		
			if(cpf.length() > 14) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF NÃO VALIDO, MAIOR QUE 14 CARACTERES");
			
			}if(nome == null || nome.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O NOME NÃO SER VAZIO OU NULO");
			
			}if(fone == null || fone.equals("")) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O TELEFONE NÃO SER VAZIO OU NULO");
				
			}else {
				
				this.clienteRepository.save(cliente);
			}
			
		return cliente;
		
	}
	
	
	//MÉTODO PARA ALTERAR CLIENTE
	public void atualizarCliente(Cliente cliente, Long id) {
		Cliente clienteBD = this.clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não encontrado"));
		BeanUtils.copyProperties(cliente, clienteBD, "id");
		this.salvarCliente(clienteBD);
	
	}
	
	
	//MÉTODO PARA EXCLUIR
	public void excluirCliente(Long id) {
		this.clienteRepository.deleteById(id);
	}

	
	//MÉTODO PARA BUSCAR CLIENTE POR ID
	public Cliente buscarClienteID(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não encontrado"));
	}
}
