package com.generation.colaboratech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.generation.colaboratech.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository <Postagem, Long> {

     List <Postagem> findAllByTituloContainingIgnoreCase(String titulo);
     
     @Query(nativeQuery = true, value = "select * from tb_postagens order by data desc")
     List <Postagem> findAllDecrescente();
     
     @Query(nativeQuery = true, value = "select * from tb_postagens order by data")
     List <Postagem> findAllCrescente();

	
     
     
}