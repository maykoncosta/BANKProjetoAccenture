package com.accenture.academico.model;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.sun.istack.NotNull;

@Entity(name = "Analista")
public class Analista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idAnalista")
	private Long idAnalista;
	
	@Column(name = "nomeAnalista")
	private String nomeAnalista;
	
	@Column(name = "cpfAnalista")
	private String cpfAnalista;
	
	@Column(name="telefoneAnalista")
	private int telefoneAnalista;
	
	@OneToMany(mappedBy = "analista")	
	private List<Cliente> cliente = new ArrayList<Cliente>();
	
	@Column(name="agencia")
	private Agencia agencia;
	
	public Analista() {
		
	}
	
	public Analista(Long idAnalista, String nomeAnalista, String cpfAnalista, int telefoneAnalista,
			List<Cliente> cliente, Agencia agencia) {
		super();
		this.idAnalista = idAnalista;
		this.nomeAnalista = nomeAnalista;
		this.cpfAnalista = cpfAnalista;
		this.telefoneAnalista = telefoneAnalista;
		this.cliente = cliente;
		this.agencia = agencia;
	}


	public boolean removerConta(int idConta) {
		return false;
		
	}
	
	public boolean validarConta(int idConta) {
		return false;
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAnalista == null) ? 0 : idAnalista.hashCode());
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
		Analista other = (Analista) obj;
		if (idAnalista == null) {
			if (other.idAnalista != null)
				return false;
		} else if (!idAnalista.equals(other.idAnalista))
			return false;
		return true;
	}
}
