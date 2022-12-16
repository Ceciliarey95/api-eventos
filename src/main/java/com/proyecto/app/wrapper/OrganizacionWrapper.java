package com.proyecto.app.wrapper;

import com.proyecto.app.dto.OrganizacionDto;
import com.proyecto.app.entity.Organizacion;

public class OrganizacionWrapper {

	public static Organizacion dtoToEntity(OrganizacionDto dto) {
		if(dto == null) {return new Organizacion();}
		
		Organizacion entity = new Organizacion();
		entity.setClave(dto.getClave());
		entity.setCuit(dto.getCuit());
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setDireccion(dto.getDireccion());
		entity.setDeleted(Boolean.FALSE);
		entity.setEventos(entity.getEventos());
		entity.setFechaAlta(entity.getFechaAlta());
		
		return entity;
	}
	
	public static OrganizacionDto entityToDto(Organizacion entity) {
		if(entity == null) {return new OrganizacionDto();}
		
		OrganizacionDto dto = new OrganizacionDto();
		dto.setClave(entity.getClave());
		dto.setCuit(entity.getCuit());
		dto.setName(entity.getName());
		dto.setEmail(entity.getEmail());
		dto.setDireccion(entity.getDireccion());	
		
		return dto;
	}
}
