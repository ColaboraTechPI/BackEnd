package com.generation.colaboratech.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.colaboratech.model.Tema;
import com.generation.colaboratech.model.Usuario;
import com.generation.colaboratech.repository.TemaRepository;

@Service
public class TemaService {

	@Autowired
	private TemaRepository temaRepository;

	public Optional<Tema> atualizarTema(Tema tema) {
//		if(temaRepository.findById(tema.getId()).isPresent()) {
//			
//			Optional<Tema> buscaNome = temaRepository.findByNome(tema.getNome());
//			
//			if ( (buscaNome.isPresent()) && ( buscaNome.get().getId() != tema.getId()))
//				throw new ResponseStatusException(
//						HttpStatus.BAD_REQUEST, "Este tema já existe!", null);
//			
//	
//			
//			return Optional.ofNullable(temaRepository.save(tema));
//			
//		}
//		
//		return Optional.empty();

		if (temaRepository.findById(tema.getId()).isPresent()) {
			if (temaRepository.findByNomeIgnoreCase(tema.getNome()).isEmpty()) {

				return Optional.ofNullable(temaRepository.save(tema));
				
			} else if (temaRepository.findByIdAndNomeIgnoreCase(tema.getId(), tema.getNome()).isPresent()) {

				return Optional.ofNullable(temaRepository.save(tema));
			}
		}
		return Optional.empty();
	}

}
