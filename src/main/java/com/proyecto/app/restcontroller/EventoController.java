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
import com.proyecto.app.dto.EventoUnicoDto;
import com.proyecto.app.entity.Evento;
import com.proyecto.app.service.IEventoService;
import com.proyecto.app.wrapper.EventoUnicoWrapper;

@RequestMapping("api/eventos")
@RestController
public class EventoController {
	private static final Logger log = LoggerFactory.getLogger(EventoController.class);

	@Autowired
	private IEventoService eventoService;
	
	@GetMapping("/evento/{nombre}")
	public ResponseEntity<Map<String, Object>> findByName(@PathVariable(name = "nombre") String nombre){
		Map<String, Object> response = new HashMap<>();
		Evento evento = eventoService.findByName(nombre);
		response.put("Evento: ", evento);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping("/registro")
	public ResponseEntity<Map<String, Object>> newEvento( @RequestBody @Valid EventoUnicoDto eventoUnicoDto) {
		Map<String, Object> response = new HashMap<>();
		EventoUnicoDto newEvento = eventoService.save(eventoUnicoDto);
		response.put("Evento: ", newEvento);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/updateEvento/{nombre}")
	public ResponseEntity<Map<String, Object>> update(@RequestBody EventoUnicoDto eventoUnicoDto){
		Map<String, Object> response = new HashMap<>();
		EventoUnicoDto updateEvento = eventoService.update(eventoUnicoDto);
		if(updateEvento == null) {
			response.put("mensaje", "No se pudo actualizar la informacion del evento.");
		}
		response.put("Evento: ", updateEvento);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{name}")
	  public ResponseEntity<Map<String, Object>> deleteOrg(@PathVariable(name = "name") String name) {
		System.out.println(name);
		Map<String, Object> response = new HashMap<>();
		Evento evento = eventoService.findByName(name);
		System.out.println(evento);
		EventoUnicoDto eventoUnicoDto = EventoUnicoWrapper.entityToDto(evento);
		EventoUnicoDto updateEvento = eventoService.delete(eventoUnicoDto);
		if(updateEvento == null) {
			response.put("mensaje", "No se pudo borrar la informacion del evento porque no existe.");
		}else {
		
		response.put("Evento eliminado: ", updateEvento);}
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	  }

	public static Logger getLog() {
		return log;
	}
}
