package com.accenture.academico.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.accenture.academico.model.ContaDigital;
import com.accenture.academico.model.Operacao;
import com.accenture.academico.model.TipoOperacao;
import com.accenture.academico.repository.ContaDigitalRepository;

@Service
public class ContaDigitalService {
	
	@Autowired
	private ContaDigitalRepository contaDigitalRepository;
	
	//MÉTODO PARA BUSCAR TODAS AS CONTAS CORRENTES
	public List<ContaDigital> buscarContaDigital(Pageable pageable){
		return this.contaDigitalRepository.findAll(pageable).getContent();
	}
	
	//MÉTODO PARA SALVAR CONTA CORRENTE
	public void salvarContaDigital(ContaDigital contaDigital) {
		this.contaDigitalRepository.save(contaDigital);
	}
	
	//MÉTODO PARA ALTERAR CONTA CORRENTE
	public void alterarContaDigital(ContaDigital contaDigital, Long id) {
		ContaDigital CD = this.contaDigitalRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agencia não encontrada"));
	    BeanUtils.copyProperties(contaDigital, CD, "id");
	    this.salvarContaDigital(CD);
	}
	
	//MÉTODO PARA EXCLUIR CONTA CORRENTE
	public void excluirContaDigital(Long id) {
		this.contaDigitalRepository.deleteById(id);
	}
	
	//MÉTODO PARA BUSCAR CONTA CORRENTE POR ID
	public ContaDigital buscarContaDigitalID(Long id) {
		Optional<ContaDigital> CC = contaDigitalRepository.findById(id);
		return CC.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Conta Digital não encontrada!"));
	}
	
	//realiza o saque da conta e retorna true se a operaçao for bem sucedida
	public boolean sacar(double valor, Long idConta) {
		
		ContaDigital contaDigital = new ContaDigital();
		contaDigital = buscarContaDigitalID(idConta);
		
		try {
			if(valor >= contaDigital.getValorsaqueminimo() && valor < contaDigital.getContaSaldo()) {
				contaDigital.setContaSaldo(contaDigital.getContaSaldo() - valor);
				Date today = Calendar.getInstance().getTime();
				Operacao operacao = new Operacao(today, TipoOperacao.SAQUE, valor);
				contaDigital.getOperacoes().add(operacao);
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public boolean debitar(double valor, Long idConta) {
		
		ContaDigital contaDigital = new ContaDigital();
		contaDigital = buscarContaDigitalID(idConta);
		
		try {
			if(valor < contaDigital.getContaSaldo()) {
				contaDigital.setContaSaldo(contaDigital.getContaSaldo() - valor);
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}	
	
	//realiza o deposito e retorna true se for bem sucedida
	public void depositar(double valor, Long idConta) {
		
		ContaDigital contaDigital = new ContaDigital();
		contaDigital = buscarContaDigitalID(idConta);
		
		try {
			contaDigital.setContaSaldo(contaDigital.getContaSaldo() + valor);
			Date today = Calendar.getInstance().getTime();
			Operacao operacao = new Operacao(today, TipoOperacao.DEPOSITO, valor);
			contaDigital.getOperacoes().add(operacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
			
	//realiza o saque de uma conta e deposita numa conta de destino
	public boolean transferir(Long idContaDestino, double valor, Long idContaOrigem) {
		
		ContaDigital contaDestino, contaOrigem;
		contaDestino = buscarContaDigitalID(idContaDestino);
		contaOrigem = buscarContaDigitalID(idContaOrigem);
		
		try {
			if(this.debitar(valor, idContaOrigem)) {
				depositar(valor, idContaDestino);
				Date today = Calendar.getInstance().getTime();
				Operacao operacao = new Operacao(today, TipoOperacao.TRANSFERENCIA, valor);
				contaOrigem.getOperacoes().add(operacao);
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
			
	public boolean pagamento(double valor, Long idConta) {
		
		ContaDigital contaDigital = new ContaDigital();
		contaDigital = buscarContaDigitalID(idConta);
		
		try {
			if(debitar(valor, idConta)) {
				Date today = Calendar.getInstance().getTime();
				Operacao operacao = new Operacao(today, TipoOperacao.PAGAMENTO, valor);
				contaDigital.getOperacoes().add(operacao);
				return true;
			}else {
				return false;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;		
	}
			
	public void rendimento(Long idConta) {
		
		ContaDigital contaDigital = new ContaDigital();
		contaDigital = buscarContaDigitalID(idConta);
		
		contaDigital.setContaSaldo(contaDigital.getContaSaldo() * contaDigital.getPercentualrendimentomes());
	}
			
	public List<Operacao> extrato(Long idConta) {
		
		ContaDigital contaDigital = new ContaDigital();
		contaDigital = buscarContaDigitalID(idConta);
		
		try {
			return contaDigital.getOperacoes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Operacao>();
	}
			
	public List<Operacao> extratoTempo(Date dataInicio, Long idConta) throws ParseException {
		
		ContaDigital contaDigital = new ContaDigital();
		contaDigital = buscarContaDigitalID(idConta);
		
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy"); 
		ArrayList<Operacao> operacoesExtrato = new ArrayList<Operacao>();
		
		try {
			
			for (Operacao operacao : contaDigital.getOperacoes()) {
				
				Date dataOperacao = formatador.parse(formatador.format(operacao.getDataHoraOperacao()));
				Date dataInicioExtrato = formatador.parse(formatador.format(dataInicio));
				
				if(dataOperacao.compareTo(dataInicioExtrato) >= 0)
					operacoesExtrato.add(operacao);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return operacoesExtrato;
	} 

}
