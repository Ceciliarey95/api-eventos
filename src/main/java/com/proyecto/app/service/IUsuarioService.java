package com.proyecto.app.service;

import java.util.List;

import com.proyecto.app.dto.UsuarioDto;
import com.proyecto.app.entity.Usuario;


public interface IUsuarioService {


		public List<Usuario> getAll();
		
		public Usuario findById(Long id);

		public UsuarioDto save(UsuarioDto usuario);
		
		public List<Usuario> findByLastname(String lastname);
		
		public UsuarioDto findByDni(Long dni);

		public UsuarioDto update(UsuarioDto usuarioDto);

		public UsuarioDto delete(UsuarioDto usuarioDto);

		public Usuario findByClave(String clave);

		
}
