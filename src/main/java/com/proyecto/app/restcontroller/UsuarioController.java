package com.proyecto.app.restcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.proyecto.app.dto.UsuarioDto;
import com.proyecto.app.entity.Usuario;
import com.proyecto.app.service.IUsuarioService;

@RequestMapping("api/usuarios")
@RestController
public class UsuarioController {
	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private IUsuarioService usuarioService;
	
	
	@GetMapping("/todosUsuarios")
	public ResponseEntity<Map<String, Object>> all(){
		Map<String, Object> response = new HashMap<>();
		List<Usuario> usuarios = new ArrayList<>();
		response.put("usuarios", usuarios);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<HashMap<String, Object>> usuario(@PathVariable(value = "id") Long id){
		HashMap<String, Object> response = new HashMap<String, Object>();
		Usuario usuario = usuarioService.findById(id);
		response.put("usuario: ", usuario);
		return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/usuario/{lastname}")
	public ResponseEntity<HashMap<String, Object>> buscarPorApellido(@PathVariable(name = "name") String lastname) throws NotFoundException{
		HashMap<String, Object> response = new HashMap<String, Object>();

		if(lastname.equals("Rey")) {
			throw new NotFoundException();
		}
		response.put("mensaje", "El usuario con apellido ".concat(lastname).concat(" existe"));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/usuario/{dni}")
	public ResponseEntity<HashMap<String, Object>> buscarPorDni(@PathVariable(name = "name") String dni) throws NotFoundException{
		HashMap<String, Object> response = new HashMap<String, Object>();

		if(dni.equals("39190124")) {
			throw new NotFoundException();
		}
		response.put("mensaje", "El usuario con DNI: ".concat(dni).concat(" existe"));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/registro")
	public ResponseEntity<Map<String, Object>> newUsuario( @RequestBody @Valid UsuarioDto usuarioDto) throws Exception{
		
		log.info("usuario: "+usuarioDto.toString());
		Map<String, Object> response = new HashMap<>();
		UsuarioDto newUsuario = usuarioService.save(usuarioDto);
		response.put("usuario: ", newUsuario);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/updateUser{id}")
	public ResponseEntity<Map<String, Object>> update(@RequestBody UsuarioDto usuarioDto){
		log.info("usuario: "+usuarioDto.toString());
		Map<String, Object> response = new HashMap<>();
		UsuarioDto updateUser = usuarioService.save(usuarioDto);
		
		if(updateUser == null) {
			response.put("mensaje", "No se pudo actualizar la informacion del usuario.");
		}
		
		response.put("usuario: ", updateUser);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{dni}")
	  public ResponseEntity<Map<String, Object>> delete(@PathVariable UsuarioDto usuarioDto) {
		Map<String, Object> response = new HashMap<>();
		UsuarioDto updateUser = usuarioService.delete(usuarioDto);
		if(updateUser == null) {
			response.put("mensaje", "No se pudo borrar la informacion del usuario.");
		}
		
		response.put("usuario: ", updateUser);
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	  }
}
