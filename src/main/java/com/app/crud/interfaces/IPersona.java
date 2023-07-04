package com.app.crud.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.crud.modelo.Persona;
//indica que esta interfaz es un repositorio de Spring Data.
@Repository
//actúa como un repositorio de Spring Data para la entidad Persona.
public interface IPersona extends CrudRepository<Persona, Integer>  {
	
	//selecciona los registros que cumplen con la siguiente condicion, la concatenación de las propiedades id, documento, nombre, apellido, correo 
	//y telefono de la entidad Persona
	@Query("SELECT p FROM Persona p WHERE "
			+ " CONCAT(p.id, p.documento, p.nombre, p.apellido, p.correo, p.telefono)"
			+ " LIKE %?1%")
	//se define un método que realiza una consulta para buscar personas que coincidan con determinada palabra clave.
	public List<Persona> findAll(String palabraClave);

}
