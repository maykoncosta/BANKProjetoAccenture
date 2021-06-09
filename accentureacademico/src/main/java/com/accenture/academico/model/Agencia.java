package com.accenture.academico.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.sun.istack.NotNull;


@Entity(name = "Agencia")
public class Agencia implements Serializable{
	
	
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idAgencia")
	private Long idAgencia;
	
	@Column(name = "nomeAgencia")
	private String nomeAgencia;
	
	@Column(name="numeroAgencia")
	private int numeroAgencia;
	
	@Column(name="telefoneAgencia")
	private int telefoneAgencia;
	
	@Column(name="dataAbertura")
	private Date dataAbertura;
	
	@OneToMany
	@JoinColumn(name = "conta_id")
	private List<ContaDigital> contas = new ArrayList<ContaDigital>();
	
	
	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public List<ContaDigital> getContas() {
		return contas;
	}

	public void setContas(List<ContaDigital> contas) {
		this.contas = contas;
	}

	public int getTelefoneAgencia() {
		return telefoneAgencia;
	}

	public void setTelefoneAgencia(int telefoneAgencia) {
		this.telefoneAgencia = telefoneAgencia;
	}

	public Long getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}

	public String getNomeAgencia() {
		return nomeAgencia;
	}

	public void setNomeAgencia(String nomeAgencia) {
		this.nomeAgencia = nomeAgencia;
	}

	public int getNumeroAgencia() {
		return numeroAgencia;
	}

	public void setNumeroAgencia(int numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}
	
	public Agencia() {
		
	}

	public Agencia(String nomeAgencia, int numeroAgencia, int telefoneAgencia, Date dataAbertura) {
		this.nomeAgencia = nomeAgencia;
		this.numeroAgencia = numeroAgencia;
		this.telefoneAgencia = telefoneAgencia;
		this.dataAbertura = dataAbertura;
	}
	
	//Foi usado anteriormente
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAgencia == null) ? 0 : idAgencia.hashCode());
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
		Agencia other = (Agencia) obj;
		if (idAgencia == null) {
			if (other.idAgencia != null)
				return false;
		} else if (!idAgencia.equals(other.idAgencia))
			return false;
		return true;
	}
	
	public List<ContaDigital> listarContas(){
		return getContas();
	}
		
	

}
