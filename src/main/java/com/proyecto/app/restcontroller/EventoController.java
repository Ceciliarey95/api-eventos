package com.proyecto.app.restcontroller;

import java.util.HashMap;
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
import com.proyecto.app.dto.EventoRecurrDto;
import com.proyecto.app.dto.EventoUnicoDto;
import com.proyecto.app.entity.Evento;
import com.proyecto.app.repository.IOrganizacionDao;
import com.proyecto.app.service.IEventoService;
import com.proyecto.app.wrapper.EventoWrapper;

@RequestMapping("api/eventos")
@RestController
public class EventoController {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(EventoController.class);

	@Autowired
	private IEventoService eventoService;
	@Autowired
	private IOrganizacionDao orgDao;
	
	@GetMapping("/evento/{nombre}")
	public ResponseEntity<Map<String, Object>> findByName(@PathVariable(name = "nombre") String nombre){
		Map<String, Object> response = new HashMap<>();
		Evento evento = eventoService.findByName(nombre);
		response.put("Evento: ", evento);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping("/registroEventoUnico")
	public ResponseEntity<Map<String, Object>> newEventoUnico( @RequestBody @Valid EventoUnicoDto eventoUnicoDto,String clave) {
		Map<String, Object> response = new HashMap<>();
		if(orgDao.findByClave(clave)!= null) {
			Evento evento = EventoWrapper.dtoToEntityU(eventoUnicoDto);
			evento = eventoService.save(evento);
			response.put("Mensaje: ", "Evento guardado con éxito!");}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	@PostMapping("/registroEventoRecurr")
	public ResponseEntity<Map<String, Object>> newEventoRecurr( @RequestBody @Valid EventoRecurrDto eventoRecurrDto,String clave) {
		Map<String, Object> response = new HashMap<>();
		if(orgDao.findByClave(clave)!= null) {
			Evento evento = EventoWrapper.dtoToEntityR(eventoRecurrDto);
			evento = eventoService.save(evento);
			response.put("Mensaje: ", "Evento guardado con éxito!");}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/updateEventoUnico/")
	public ResponseEntity<Map<String, Object>> update(@RequestBody EventoUnicoDto eventoUnicoDto, String claveOrg){
		Map<String, Object> response = new HashMap<>();
		if(orgDao.findByClave(claveOrg)!=null){
			Evento evento = EventoWrapper.dtoToEntityU(eventoUnicoDto);
		    Evento updateEvento = eventoService.update(evento);
		    if(updateEvento == null) {
		    	response.put("mensaje", "No se pudo actualizar la informacion del evento.");
		    }
		response.put("Evento: ", updateEvento);}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	@PutMapping("/updateEventoRecurr/")
	public ResponseEntity<Map<String, Object>> update(@RequestBody EventoRecurrDto eventoRecurrDto, String claveOrg){
		Map<String, Object> response = new HashMap<>();
		if(orgDao.findByClave(claveOrg)!=null){
			Evento evento = EventoWrapper.dtoToEntityR(eventoRecurrDto);
			Evento updateEvento = eventoService.update(evento);
			if(updateEvento == null) {
				response.put("mensaje", "No se pudo actualizar la informacion del evento.");
				}
			response.put("Evento: ", updateEvento);}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/")
	  public ResponseEntity<Map<String, Object>> deleteOrg(@RequestBody String name, String claveOrg) {
		Map<String, Object> response = new HashMap<>();
		if(orgDao.findByClave(claveOrg)!=null){
			Evento evento = eventoService.findByName(name);
			Evento updateEvento = eventoService.delete(evento);
			response.put("Evento eliminado: ", updateEvento);}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	  }

}
