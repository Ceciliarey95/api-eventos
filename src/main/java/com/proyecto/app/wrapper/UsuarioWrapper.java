package com.proyecto.app.wrapper;

import com.proyecto.app.dto.UsuarioDto;
import com.proyecto.app.entity.Usuario;

public class UsuarioWrapper {

	public static Usuario dtoToEntity(UsuarioDto dto) {
		if(dto == null) return new Usuario();
		
		Usuario entity = new Usuario();
		entity.setClave(dto.getClave());
		entity.setDni(dto.getDni());
		entity.setLastname(dto.getLastname());
		entity.setName(dto.getName());
		
		
		return entity;
	}
	
	public static UsuarioDto entityToDto(Usuario entity) {
		if(entity == null) return new UsuarioDto();
		
		UsuarioDto dto = new UsuarioDto();
		dto.setClave(entity.getClave());
		dto.setDni(entity.getDni());
		dto.setLastname(entity.getLastname());
		dto.setName(entity.getName());
		
		return dto;
	}
}
