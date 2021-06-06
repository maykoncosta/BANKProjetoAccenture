package com.accenture.academico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.academico.model.ContaDigital;

public interface ContaCorrenteRepository extends JpaRepository<ContaDigital, Long>{

}
