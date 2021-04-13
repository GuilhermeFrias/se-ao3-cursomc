package com.guilhermefrias.cursomc.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.guilhermefrias.cursomc.domain.Categoria;
import com.guilhermefrias.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id); 
		return obj.orElse(null); 

}
}
