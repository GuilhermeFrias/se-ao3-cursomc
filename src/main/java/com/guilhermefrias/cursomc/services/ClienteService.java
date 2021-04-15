package com.guilhermefrias.cursomc.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.guilhermefrias.cursomc.domain.Cliente;
import com.guilhermefrias.cursomc.repositories.ClienteRepository;
import com.guilhermefrias.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {     
		Optional<Cliente> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException("objeto nao encontrado! ID: " + id + ", tipo" + Cliente.class.getName()));
	}
	
}
