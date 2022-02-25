package com.generation.colaboratech.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.colaboratech.model.Tema;
import com.generation.colaboratech.repository.TemaRepository;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {
	
	@Autowired
	private TemaRepository temaRepository;
	
	
	@PostMapping
	public ResponseEntity<Tema> postTema(@Valid @RequestBody Tema tema)
	{
		if(temaRepository.existsByNomeContainingIgnoreCase(tema.getNome()))
		{
			return new ResponseEntity("Este tema já existe.",HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED)
	
			.body(temaRepository.save(tema));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tema> getById(@PathVariable Long id){
		return temaRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Tema>> getByNome (@PathVariable String nome){
		return ResponseEntity.ok(temaRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	
	
	@GetMapping
	public ResponseEntity<List<Tema>> getAll()
	{
		return ResponseEntity.ok(temaRepository.findAll());
	}
	
	@PutMapping
	public ResponseEntity<Tema> putTema(@Valid @RequestBody Tema tema)
	{
		return temaRepository.findById(tema.getId())
				.map(resposta ->
				ResponseEntity.status(HttpStatus.OK)
				.body(temaRepository.save(tema)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteTema(@PathVariable Long id)
	{
		return temaRepository.findById(id)
				.map(resposta ->
				{
					temaRepository.deleteById(id);
					return ResponseEntity.noContent().build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
}
