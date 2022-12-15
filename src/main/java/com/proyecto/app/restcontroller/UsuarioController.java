package com.proyecto.app.restcontroller;

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
import com.proyecto.app.wrapper.UsuarioWrapper;

@RequestMapping("api/usuarios")
@RestController
public class UsuarioController {
	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private IUsuarioService usuarioService;
	
	
	@GetMapping("/todosUsuarios")
	public ResponseEntity<Map<String, Object>> all(){
		Map<String, Object> response = new HashMap<>();
		List<Usuario> usuarios = usuarioService.getAll();
		response.put("Usuarios: ", usuarios);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
	
	@GetMapping("/usuarioById/{id}")
	public ResponseEntity<Map<String, Object>> usuario(@PathVariable(name = "id") Long id){
		Map<String, Object> response = new HashMap<>();
		Usuario usuario = usuarioService.findById(id);
		response.put("Usuario: ", usuario);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/usuarioByLastname/{lastname}")
	public ResponseEntity<Map<String, Object>> findByLastname(@PathVariable(name = "lastname") String lastname) throws NotFoundException{
		Map<String, Object> response = new HashMap<>();
		List<Usuario> usuarios = usuarioService.findByLastname(lastname);
		if(usuarios!=null) {
			response.put("Usuario: ", usuarios);
		}else {
			response.put("Mensaje", "El usuario no existe!");
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/usuarioByDni/{dni}")
	public ResponseEntity<Map<String, Object>> buscarPorDni(@PathVariable(name = "dni") Long dni) throws NotFoundException{
		Map<String, Object> response = new HashMap<>();
		UsuarioDto usuario = usuarioService.findByDni(dni);
		if(usuario!=null) {
			response.put("Usuario: ", usuario);
		}else {
			response.put("Mensaje", "El usuario no existe!");
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/registro")
	public ResponseEntity<Map<String, Object>> newUsuario( @RequestBody @Valid UsuarioDto usuarioDto) throws Exception{
		
		log.info("usuario: "+usuarioDto.toString());
		Map<String, Object> response = new HashMap<>();
		UsuarioDto newUsuario = usuarioService.save(usuarioDto);
		response.put("Usuario: ", newUsuario);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<Map<String, Object>> update(@RequestParam(value="clave",required = true) String clave,@RequestBody UsuarioDto usuarioDto){
		log.info("usuario: "+usuarioDto.toString());
		Map<String, Object> response = new HashMap<>();
		Usuario usuario = usuarioService.findByClave(clave);
		if(usuario!=null) {
		UsuarioDto updateUser = usuarioService.update(usuarioDto);
		response.put("Usuario: ", updateUser);
		}else{
			response.put("mensaje", "No se pudo actualizar la informacion del usuario.");
		}
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	  public ResponseEntity<Map<String, Object>> delete(@RequestParam(value="clave",required = true) String clave) {
		Map<String, Object> response = new HashMap<>();
		Usuario usuario = usuarioService.findByClave(clave);
		if(usuario!=null) {
		UsuarioDto usuarioDto = UsuarioWrapper.entityToDto(usuario);
		UsuarioDto updateUser = usuarioService.delete(usuarioDto);
		response.put("Usuario: ", updateUser);
		}else {
			response.put("mensaje", "No se pudo borrar la informacion del usuario.");
		}
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	  }
}
