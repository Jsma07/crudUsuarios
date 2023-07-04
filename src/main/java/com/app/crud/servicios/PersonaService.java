package com.app.crud.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.crud.interfaceService.IpersonaService;
import com.app.crud.interfaces.IPersona;
import com.app.crud.modelo.Persona;
@Service
public class PersonaService implements IpersonaService{
	@Autowired
	private IPersona data;
	
	@Override
	public List<Persona> listar(String palabraClave) {
		if(palabraClave!= null) {
			return data.findAll(palabraClave);
		}
		return (List<Persona>)data.findAll();
	}

	@Override
	public Optional<Persona> listarId(int id) {
		
		return data.findById(id);
	}

	@Override
	public int save(Persona p) {
		// TODO Auto-generated method stub
		int respuesta=0;
		Persona persona = data.save(p);
		if(!persona.equals(null)) {
			respuesta = 1;
		}
		return respuesta;
	}

	@Override
	public void delete(int id) {
		data.deleteById(id);
		
	}

	
}
