package com.app.crud.interfaceService;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.app.crud.modelo.Persona;

public interface IpersonaService {
	@Query("SELECT p FROM Persona p WHERE p.nombre LIKE %?1%")
	
	public List<Persona>listar(String palabraClave);
	public Optional<Persona>listarId(int id);
	public int save(Persona p);
	public void delete(int id);
}
