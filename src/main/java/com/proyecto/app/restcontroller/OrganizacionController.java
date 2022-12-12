package com.proyecto.app.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.proyecto.app.wrapper.OrganizacionWrapper;

@RequestMapping("api/organizaciones")
@RestController
public class OrganizacionController {
	private static final Logger log = LoggerFactory.getLogger(OrganizacionController.class);
	
	@Autowired
	private IOrganizacionService organizacionService;
	
	@GetMapping("/todasOrg/{deleted}")
	public ResponseEntity<Map<String, Object>> findByDeleted(@PathVariable(name = "deleted") Boolean deleted){
		Map<String, Object> response = new HashMap<>();
		List<Organizacion> organizaciones = organizacionService.findByDeleted(deleted);
		response.put("organizaciones", organizaciones);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
	
	@GetMapping("/org2/{cuit}")
	public ResponseEntity<Map<String, Object>> findByCuit(@PathVariable(name = "cuit") Long cuit){
		Map<String, Object> response = new HashMap<>();
		Organizacion newOrg = organizacionService.findByCuit(cuit);
		if (newOrg != null) {
		response.put("Organizacion: ", newOrg);
		}else{
		response.put("mensaje", "La organizacion con Cuit: "+ cuit +" no existe");
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/org/{name}")
	public ResponseEntity<Map<String, Object>> findByNombre(@PathVariable(name = "name") String name){
		Map<String, Object> response = new HashMap<>();
		Organizacion org = organizacionService.findByName(name);
		response.put("Organización: ",org );
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/registro")
	public ResponseEntity<Map<String, Object>> newOrganizacion( @RequestBody @Valid OrganizacionDto orgDto) throws Exception{
		Map<String, Object> response = new HashMap<>();
		OrganizacionDto newOrganizacion = organizacionService.save(orgDto);
		response.put("Organizacion: ", newOrganizacion);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/updateOrg/{cuit}")
	public ResponseEntity<Map<String, Object>> update(@RequestBody OrganizacionDto orgDto){
		log.info("Organizacion: "+orgDto.toString());
		Map<String, Object> response = new HashMap<>();
		OrganizacionDto updateOrg = organizacionService.update(orgDto);
		
		if(updateOrg == null) {
			response.put("mensaje", "No se pudo actualizar la informacion de la organizacion.");
		}
		
		response.put("Organizacion: ", updateOrg);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{cuit}")
	  public ResponseEntity<Map<String, Object>> deleteOrg(@PathVariable(value="cuit") long cuit) {
		Map<String, Object> response = new HashMap<>();
		Organizacion org = organizacionService.findByCuit(cuit);
		OrganizacionDto orgDto = OrganizacionWrapper.entityToDto(org);
		OrganizacionDto updateOrg = organizacionService.delete(orgDto);
		if(updateOrg == null) {
			response.put("mensaje", "No se pudo borrar la informacion de la organizacion porque no existe.");
		}
		
		response.put("Organizacion eliminada: ", updateOrg);
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	  }
}
