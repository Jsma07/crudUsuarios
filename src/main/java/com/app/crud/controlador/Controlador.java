package com.app.crud.controlador;

// se importan las clases y anotaciones necesarias para el controlador
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.app.crud.interfaceService.IpersonaService;
import com.app.crud.modelo.Persona;

// se declara como un controlador de spring
@Controller
@RequestMapping
public class Controlador {
	//Se inyecta la implementacion de la instancia en la variable 
	@Autowired
	//Se define una variable que seria una instacia de IpersonaService
    private IpersonaService service;
	
	//se le asigna una url que se ejecutara cuando se haga una consulta 
	@GetMapping("/clientes")
	
	public String listar(Model model, @Param("palabraClave")String palabraClave) {
		// metodo listar que se le pasa el parametro "palabra clave" 
		//y devuelve una lista de personas que se guardan en la variable personas
		List<Persona> personas = service.listar(palabraClave);
		// el atributo "personas" contiene la lista obtenida anteriormente para que la vista pueda acceder y mostrar los datos
		model.addAttribute("personas", personas);
		// se agrega un atributo al objeto model el cual permitira mantener el valor ingresado por el usuario en la búsqueda y mostrarlo en la vista.
		model.addAttribute("palabraClave", palabraClave);
		// devuelve al index que mostrara los resultados
		return "index";
	}
	
	//se le asigna una url que se ejecutara cuando se realice una solicitud 
	@GetMapping("/new")
	//se crea una instancia vacia de persona y se agrega al modelo
	//devuelve la vista form que mostrará el formulario vacío
	public String agregar(Model model) {
		model.addAttribute("persona", new Persona());
		return "form";
	}
	//este método guarda los datos del cliente en la base de datos utilizando el objeto service 
	// y luego redirecciona al usuario a la página que muestra la lista de clientes.
	@PostMapping("/save")
	public String save(@Validated Persona p, Model model) {
		service.save(p);
		return "redirect:/clientes";
		
	}
	//este método obtiene los datos de una persona específica por su ID, los agrega al objeto Model y devuleve la vista form
	//donde se mostrarán los datos en el formulario.
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable int id, Model model) {
		Optional<Persona>persona= service.listarId(id);
		model.addAttribute("persona", persona);
		return "form";
		 
	}
	//este método elimina una persona específica por su ID utilizando el objeto service despues envia al usuario a la página que muestra la lista
	//actualizada de clientes.
	@GetMapping("/eliminar/{id}")
	public String delete(Model model, @PathVariable int id) {
		service.delete(id);
		return "redirect:/clientes";
		
	}
}
