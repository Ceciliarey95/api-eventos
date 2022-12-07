package com.proyecto.app.restcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.app.dto.OrganizacionDto;
import com.proyecto.app.entity.Organizacion;
import com.proyecto.app.service.IOrganizacionService;


@RequestMapping("api/organizaciones")
@RestController
public class OrganizacionController {
	private static final Logger log = LoggerFactory.getLogger(OrganizacionController.class);
	
	@Autowired
	private IOrganizacionService organizacionService;
	
	@GetMapping("/todasOrg")
	public ResponseEntity<Map<String, Object>> all(){
		Map<String, Object> response = new HashMap<>();
		List<Organizacion> organizaciones = new ArrayList<>();
		response.put("organizaciones", organizaciones);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	@GetMapping("/org/{cuit}")
	public ResponseEntity<HashMap<String, Object>> buscarPorCuit(@PathVariable(name = "name") String cuit) throws NotFoundException{
		HashMap<String, Object> response = new HashMap<String, Object>();

		if(cuit.equals("23391901244")) {
			throw new NotFoundException();
		}
		response.put("mensaje", "La organizacion con Cuit: ".concat(cuit).concat(" existe"));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/org/{nombre}")
	public ResponseEntity<HashMap<String, Object>> buscarPorNombre(@PathVariable(name = "nombre") String nombre) throws NotFoundException{
		HashMap<String, Object> response = new HashMap<String, Object>();

		if(nombre.equals("Info")) {
			throw new NotFoundException();
		}
		response.put("mensaje", "La organizacion con nombre ".concat(nombre).concat(" existe"));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/registro")
	public ResponseEntity<Map<String, Object>> newOrganizacion( @RequestBody @Valid OrganizacionDto orgDto) throws Exception{
		
		log.info("Organizacion: "+orgDto.toString());
		Map<String, Object> response = new HashMap<>();
		OrganizacionDto newOrganizacion = organizacionService.save(orgDto);
		response.put("Organizacion: ", newOrganizacion);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/updateOrg{id}")
	public ResponseEntity<Map<String, Object>> update(@RequestBody OrganizacionDto orgDto){
		log.info("Organizacion: "+orgDto.toString());
		Map<String, Object> response = new HashMap<>();
		OrganizacionDto updateOrg = organizacionService.save(orgDto);
		
		if(updateOrg == null) {
			response.put("mensaje", "No se pudo actualizar la informacion de la organizacion.");
		}
		
		response.put("Organizacion: ", updateOrg);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{cuit}")
	  public ResponseEntity<Map<String, Object>> delete(@PathVariable OrganizacionDto orgDto) {
		Map<String, Object> response = new HashMap<>();
		OrganizacionDto updateOrg = organizacionService.delete(orgDto);
		if(updateOrg == null) {
			response.put("mensaje", "No se pudo borrar la informacion de la organizacion.");
		}
		
		response.put("Organizacion: ", updateOrg);
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	  }
}
