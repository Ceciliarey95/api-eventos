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
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	@PostMapping("/registroEventoUnico/")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> newTurno(@RequestParam(value="eventoUnico",required =true) String name, @RequestParam(value="clave",required = true) String clave, @RequestBody @Valid TurnoEventoUnicoDto turnoEventoUnicoDto) throws Exception{
		Map<String, Object> response = new HashMap<>();
		TurnoEventoUnicoDto newTurno = turnoService.saveEventoAndUsuario(name, clave);
		response.put("Turno: ", newTurno);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	@PostMapping("/registroEventoRecurr/")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> newTurno(@RequestParam(value="eventoRecurr",required =true) String name, @RequestParam(value="clave",required = true) String clave, @RequestBody @Valid TurnoEventoRecurrDto turnoEventoRecurrDto) throws Exception{
		Map<String, Object> response = new HashMap<>();
		TurnoEventoRecurrDto newTurno = turnoService.saveEventoAndUsuario(name, clave);
		response.put("Turno: ", newTurno);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/todosTurnos/{activo}")
	public ResponseEntity<Map<String, Object>> findByActivo(@RequestParam(value="organizacion",required =true) String name,@PathVariable(name = "activo") Boolean activo){
		Map<String, Object> response = new HashMap<>();
		
		
		
		
		List<Turno> turnos = turnoService.findByActivo(activo);
		response.put("Turnos: ", turnos);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	public static Logger getLog() {
		return log;
	}

}
