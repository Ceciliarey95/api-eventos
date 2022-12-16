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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.proyecto.app.dto.TurnoEventoRecurrDto;
import com.proyecto.app.dto.TurnoEventoUnicoDto;
import com.proyecto.app.entity.Evento;
import com.proyecto.app.entity.Turno;
import com.proyecto.app.entity.Usuario;
import com.proyecto.app.service.IEventoService;
import com.proyecto.app.service.ITurnoService;
import com.proyecto.app.service.IUsuarioService;
import com.proyecto.app.wrapper.TurnoEventoWrapper;

@RequestMapping("api/turnos")
@RestController
public class TurnoController {

	private static final Logger log = LoggerFactory.getLogger(TurnoController.class);

	@Autowired
	private ITurnoService turnoService;
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IEventoService eventoService;
	
	
	@PostMapping("/registroTurnoEventoUnico/")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> newTurno(@RequestBody @Valid TurnoEventoUnicoDto turnoEventoUnicoDto) throws Exception{
		Map<String, Object> response = new HashMap<>();
		Usuario usuario = usuarioService.findByClave(turnoEventoUnicoDto.getClave());
		Evento evento = eventoService.findByName(turnoEventoUnicoDto.getNameEvento());
		if(evento!= null && usuario != null) {
			Turno turno = TurnoEventoWrapper.dtoToEntityU(turnoEventoUnicoDto);
			turno.setEvento(evento);
			turno.setUsuario(usuario);
			turno.setFechaHora(evento.getFechaEvento());
			turno = turnoService.save(turno);
			response.put("Mensaje", "El turno se guardó con exito!");
		}else {
				response.put("Mensaje:", "El turno no se pudo guardar!");}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	@PostMapping("/registroTurnoEventoRecurr/")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> newTurno(@RequestBody @Valid TurnoEventoRecurrDto turnoEventoRecurrDto) throws Exception{
		Map<String, Object> response = new HashMap<>();
		Usuario usuario = usuarioService.findByClave(turnoEventoRecurrDto.getClave());
		Evento evento = eventoService.findByName(turnoEventoRecurrDto.getNameEvento());
		if(evento!= null && usuario != null) {
			Turno turno = TurnoEventoWrapper.dtoToEntityR(turnoEventoRecurrDto);
			turno.setEvento(evento);
			turno.setUsuario(usuario);
			turno = turnoService.save(turno);
			response.put("Mensaje", "El turno se guardó con exito!");
		}else {
				response.put("Mensaje:", "El turno no se pudo guardar!");}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/todosTurnosActivosOrg/")
	public ResponseEntity<Map<String, Object>> findByActivoAndOrganizacion(@RequestBody String name){
		Map<String, Object> response = new HashMap<>();
		List<Turno> turnos = turnoService.findByActivoAndOrganizacion(true, name);
		response.put("Turnos: ", turnos);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	@GetMapping("/todosTurnosInactivosOrg/")
	public ResponseEntity<Map<String, Object>> findByInactivoAndOrganizacion(@RequestParam(value="organizacion",required =true) String name){
		Map<String, Object> response = new HashMap<>();
		List<Turno> turnos = turnoService.findByActivoAndOrganizacion(false, name);
		response.put("Turnos: ", turnos);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	public static Logger getLog() {
		return log;
	}

}
