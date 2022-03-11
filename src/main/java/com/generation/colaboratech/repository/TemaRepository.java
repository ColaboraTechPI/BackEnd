package com.generation.colaboratech.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.colaboratech.model.Tema;

@Repository
public interface TemaRepository extends JpaRepository <Tema, Long>{
	
	public List <Tema> findAllByNomeContainingIgnoreCase(String nome);
	
	public boolean existsByNomeIgnoreCase (String nome); //adicionando opção limitação de temas
	public Optional<Tema> findByNomeIgnoreCase (String nome);

	public Optional<Tema> findByIdAndNomeIgnoreCase(Long id, String nome);
}
