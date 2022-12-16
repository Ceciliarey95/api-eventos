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
import com.proyecto.app.entity.Organizacion;
import com.proyecto.app.repository.IOrganizacionDao;
import com.proyecto.app.service.IEventoService;
import com.proyecto.app.service.IOrganizacionService;
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
	@Autowired
	private IOrganizacionService organizacionService;
	
	@GetMapping("/evento/{nombre}")
	public ResponseEntity<Map<String, Object>> findByName(@PathVariable(name = "nombre") String nombre){
		Map<String, Object> response = new HashMap<>();
		Evento evento = eventoService.findByName(nombre);
		response.put("Evento: ", evento);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping("/registroEventoUnico")
	public ResponseEntity<Map<String, Object>> newEventoUnico( @RequestBody @Valid EventoUnicoDto eventoUnicoDto ) {
		Map<String, Object> response = new HashMap<>();
		Organizacion org = organizacionService.findByClave(eventoUnicoDto.getClave());
		if(org!= null) {
			Evento evento = EventoWrapper.dtoToEntityU(eventoUnicoDto);
			evento.setOrganizacion(org);
			evento = eventoService.save(evento);
			response.put("Mensaje: ", "Evento guardado con éxito!");
			}else {
				response.put("Mensaje:", "El evento no se pudo registrar con éxito.");}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/registroEventoRecurr")
	public ResponseEntity<Map<String, Object>> newEventoRecurr( @RequestBody @Valid EventoRecurrDto eventoRecurrDto) {
		Map<String, Object> response = new HashMap<>();
		Organizacion org = organizacionService.findByClave(eventoRecurrDto.getClave());
		if(org != null) {
			Evento evento = EventoWrapper.dtoToEntityR(eventoRecurrDto);
			evento.setOrganizacion(org);
			evento = eventoService.save(evento);
			response.put("Mensaje: ", "Evento guardado con éxito!");
			}else {
				response.put("Mensaje: ", "No se pudo registrar el evento");}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}

	@PutMapping("/updateEventoRecurr/{name}")
	public ResponseEntity<Map<String, Object>> updateR(@PathVariable (name="name") String name,@RequestBody EventoRecurrDto eventoRecurrDto){
		Map<String, Object> response = new HashMap<>();
		Organizacion org = organizacionService.findByClave(eventoRecurrDto.getClave());
		Evento evento    = eventoService.findByName(name);
		Evento eventoR = EventoWrapper.dtoToEntityR(eventoRecurrDto);
		if(org !=null && (org.getId()==evento.getOrganizacion().getId()) ){
			eventoR.setOrganizacion(org);
			evento = eventoService.update(eventoR);
			response.put("Mensaje: ", "Evento actualizado!");
			}else{
			response.put("Mensaje: ", "La clave es incorrecta o no corresponde a la organizacion encargada de este evento!");}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/updateEventoUnico/{name}")
	public ResponseEntity<Map<String, Object>> updateU(@PathVariable (name="name") String name, @RequestBody EventoUnicoDto eventoUnicoDto){
		Map<String, Object> response = new HashMap<>();
		//Obtengo la organizacion con la clave por body
		Organizacion org = organizacionService.findByClave(eventoUnicoDto.getClave());
		//Obtengo el evento con el nombre en el path
		Evento evento    = eventoService.findByName(name);
		//Paso el evento que se pasó en el body a una entidad
		Evento eventoU   = EventoWrapper.dtoToEntityU(eventoUnicoDto);
		//Validamos la clave y que este evento corresponda a esta organización
		if(org !=null && (org.getId()==evento.getOrganizacion().getId())){
			//Guardo el evento que se pasó en el body como la entidad que obtuvimos
			eventoU.setOrganizacion(org);
			evento = eventoService.save(eventoU);
			response.put("Mensaje: ", "Evento actualizado!");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
			}else {
			response.put("Mensaje: ", "La clave es incorrecta o no corresponde a la organizacion encargada de este evento!");
			}
		response.put("Mensaje: ", "No se pudo actualizar el evento!");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{nombreEvento}")
	  public ResponseEntity<Map<String, Object>> deleteOrg(@PathVariable (name="nombreEvento") String name,@RequestBody String clave) {
		Map<String, Object> response = new HashMap<>();
		Evento evento = eventoService.findByName(name);
		EventoUnicoDto eventoUDto = EventoWrapper.entityToDtoU(evento);
		EventoRecurrDto eventoRDto = EventoWrapper.entityToDtoR(evento);
		if(eventoUDto!=null || eventoRDto!=null){
			if(orgDao.findByClave(clave)!=null ) {			
			Evento updateEvento = eventoService.delete(evento);
			response.put("Evento eliminado: ", updateEvento);}else{
				response.put("Mensaje: ","La clave es incorrecta");}}else{
					response.put("Mensaje:" , "No exise el evento!"); }
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	  }

}
