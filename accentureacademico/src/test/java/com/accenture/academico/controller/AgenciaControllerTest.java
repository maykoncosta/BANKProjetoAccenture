package com.accenture.academico.controller;


import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;





import com.accenture.academico.model.Agencia;


@SpringBootTest()
class AgenciaControllerTest{
	
	private final Agencia agenciaTeste = new Agencia("Agência - Recife", 0047, 21198240);
	
	private final Agencia agenciaTeste2 = new Agencia("Agência - São Paulo", 0040, 21198550);
	

	@Mock
	private AgenciaController agenciaController;
	
	@BeforeEach
	public void setUp() {
		agenciaController.salvarAgencia(agenciaTeste2);
	}
	
	@Test
	void testSalvarAgencia(){
		
		agenciaController.salvarAgencia(agenciaTeste);
		
	}

	
	@Test
	void testBuscarAgenciaPeloId() throws Exception{
		
		Agencia agenciaEsperada = agenciaTeste2 ;
		
		Agencia agenciaObtida = agenciaController.buscarAgenciaID(agenciaTeste.getIdAgencia());
		
		assertNotEquals(agenciaEsperada, agenciaObtida);
		
	}
	
	
	
}
