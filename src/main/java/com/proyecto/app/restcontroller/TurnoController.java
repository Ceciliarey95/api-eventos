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
import com.proyecto.app.entity.Turno;
import com.proyecto.app.service.ITurnoService;

@RequestMapping("api/turnos")
@RestController
public class TurnoController {

	private static final Logger log = LoggerFactory.getLogger(TurnoController.class);

	@Autowired
	private ITurnoService turnoService;
	
	
	@PostMapping("/registroTurnoEventoUnico/")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> newTurno(@RequestBody @Valid TurnoEventoUnicoDto turnoEventoUnicoDto,@RequestBody String clave,@RequestBody String name) throws Exception{
		Map<String, Object> response = new HashMap<>();
		TurnoEventoUnicoDto newTurno = turnoService.saveEventoAndUsuarioTU(name, clave);
		newTurno = turnoService.saveTurnoEvU(turnoEventoUnicoDto);
		response.put("Turno: ", newTurno);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	@PostMapping("/registroTurnoEventoRecurr/")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> newTurno(@RequestBody @Valid TurnoEventoRecurrDto turnoEventoRecurrDto,@RequestBody String nombreEventoRecurr,@RequestBody String claveUsuario) throws Exception{
		Map<String, Object> response = new HashMap<>();
		TurnoEventoRecurrDto newTurno = turnoService.saveEventoAndUsuarioTR(nombreEventoRecurr, claveUsuario);
		response.put("Turno: ", newTurno);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/todosTurnosActivosOrg/")
	public ResponseEntity<Map<String, Object>> findByActivoAndOrganizacion(@RequestParam(value="organizacion",required =true) String name){
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
