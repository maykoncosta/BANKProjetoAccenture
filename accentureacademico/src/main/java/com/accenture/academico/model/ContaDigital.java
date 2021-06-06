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
	
	//realiza o saque da conta e retorna true se a operaçao for bem sucedida
		public boolean sacar(double valor) {
			try {
				if(valor >= valorSaqueMinimo && valor < getContaSaldo()) {
					setContaSaldo(getContaSaldo() - valor);
					Date today = Calendar.getInstance().getTime();
					Operacao operacao = new Operacao(today, TipoOperacao.SAQUE, valor);
					this.operacoes.add(operacao);
					return true;
				}else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
			
		}
		
		public boolean debitar(double valor) {
			try {
				if(valor < getContaSaldo()) {
					setContaSaldo(getContaSaldo() - valor);
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
		public void depositar(double valor) {
			try {
				setContaSaldo(getContaSaldo() + valor);
				Date today = Calendar.getInstance().getTime();
				Operacao operacao = new Operacao(today, TipoOperacao.DEPOSITO, valor);
				this.operacoes.add(operacao);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		//realiza o saque de uma conta e deposita numa conta de destino
		public boolean transferir(ContaDigital contaDestino, double valor) {
			try {
				if(debitar(valor)) {
					contaDestino.depositar(valor);
					Date today = Calendar.getInstance().getTime();
					Operacao operacao = new Operacao(today, TipoOperacao.TRANSFERENCIA, valor);
					this.operacoes.add(operacao);
					return true;
				}else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
		
		public boolean pagamento(double valor) {
			try {
				if(debitar(valor)) {
					Date today = Calendar.getInstance().getTime();
					Operacao operacao = new Operacao(today, TipoOperacao.PAGAMENTO, valor);
					this.operacoes.add(operacao);
					return true;
				}else {
					return false;
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;		
		}
		
		public void rendimento() {
			setContaSaldo(getContaSaldo() * percentualRendimentoMes);
		}
		
		public List<Operacao> extrato() {
			try {
				return getOperacoes();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ArrayList<Operacao>();
		}
		
		public List<Operacao> extratoTempo(Date dataInicio) throws ParseException {
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy"); 
			ArrayList<Operacao> operacoesExtrato = new ArrayList<Operacao>();
			
			try {
				
				for (Operacao operacao : operacoes) {
					
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
