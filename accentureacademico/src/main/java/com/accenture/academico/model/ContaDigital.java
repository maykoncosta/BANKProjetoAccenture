package com.accenture.academico.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="ContaDigital")
public class ContaDigital implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final double percentualRendimentoMes = 1.015;
	private static final double valorSaqueMinimo = 20;
	private static final int periodoRendimento = 30;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idConta")
	private Long idConta;
	
	@Column(name = "dataCriacao")
	private Date dataCriacao;
	
	@Column(name = "NumConta")
	private int contaNumero;
	
	@Column(name = "saldo")
	private double contaSaldo;
	
	@OneToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "operacao")	
	private List<Operacao> operacoes = new ArrayList<Operacao>();

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static double getPercentualrendimentomes() {
		return percentualRendimentoMes;
	}

	public double getValorsaqueminimo() {
		return valorSaqueMinimo;
	}

	public static int getPeriodorendimento() {
		return periodoRendimento;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public List<Operacao> getOperacoes() {
		return operacoes;
	}

	public void setOperacoes(List<Operacao> operacoes) {
		this.operacoes = operacoes;
	}

	public Long getIdConta() {
		return idConta;
	}

	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}


	public int getContaNumero() {
		return contaNumero;
	}

	public void setContaNumero(int contaNumero) {
		this.contaNumero = contaNumero;
	}

	public double getContaSaldo() {
		return contaSaldo;
	}

	public void setContaSaldo(double contaSaldo) {
		this.contaSaldo = contaSaldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idConta == null) ? 0 : idConta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaDigital other = (ContaDigital) obj;
		if (idConta == null) {
			if (other.idConta != null)
				return false;
		} else if (!idConta.equals(other.idConta))
			return false;
		return true;
	}
		
}
