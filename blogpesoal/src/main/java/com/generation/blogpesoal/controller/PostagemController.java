package com.generation.blogpesoal.controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.generation.blogpesoal.model.Postagem;
import com.generation.blogpesoal.repository.PostagemRepository;
import com.generation.blogpesoal.repository.TemaRespository;


@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private TemaRespository temaRepository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable long id){
		return postagemRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); 
	}
		
	@GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
    	return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo)); 
	}
	
	@PostMapping
	public ResponseEntity<Postagem> post(@PathVariable Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem){
		if (postagemRepository.existsById(postagem.getId())){
			
		}
				if (temaRepository.existsById(postagem.getTema().getId()))
					return ResponseEntity.status(HttpStatus.OK)
							.body(postagemRepository.save(postagem));
				
				return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
					
		}
	
	}
    
  



