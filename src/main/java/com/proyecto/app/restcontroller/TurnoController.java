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
import com.proyecto.app.dto.TurnoDto;
import com.proyecto.app.entity.Turno;
import com.proyecto.app.service.ITurnoService;

@RequestMapping("api/turnos")
@RestController
public class TurnoController {

	private static final Logger log = LoggerFactory.getLogger(TurnoController.class);

	@Autowired
	private ITurnoService turnoService;
	
	
	@PostMapping("/registro/")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> newTurno(@RequestParam(value="evento",required =true) String name, @RequestParam(value="clave",required = true) String clave, @RequestBody @Valid TurnoDto turnoDto) throws Exception{
		Map<String, Object> response = new HashMap<>();
		TurnoDto newTurno = turnoService.saveEventoAndUsuario(name, clave);
		response.put("Turno: ", newTurno);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/todosTurnos/{activo}")
	public ResponseEntity<Map<String, Object>> findByActivo(@PathVariable(name = "activo") Boolean activo){
		Map<String, Object> response = new HashMap<>();
		List<Turno> turnos = turnoService.findByActivo(activo);
		response.put("Turnos: ", turnos);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	public static Logger getLog() {
		return log;
	}

}
