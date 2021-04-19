package com.guilhermefrias.cursomc.services;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.guilhermefrias.cursomc.domain.Categoria;
import com.guilhermefrias.cursomc.repositories.CategoriaRepository;
import com.guilhermefrias.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {     
		Optional<Categoria> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException("objeto nao encontrado! ID: " + id + ", tipo" + Categoria.class.getName()));
	}
	
	public Categoria insert (Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update (Categoria obj) {
		buscar(obj.getId());
		return repo.save(obj);
	}
	
	
}
