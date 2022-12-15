package com.proyecto.app.wrapper;

import com.proyecto.app.dto.EventoUnicoDto;
import com.proyecto.app.entity.Evento;

public class EventoUnicoWrapper {
	
	public static Evento dtoToEntity(EventoUnicoDto dto) {
		if(dto == null) return new Evento();
		
		Evento entity = new Evento();
		entity.setName(dto.getName());
		entity.setDireccion(dto.getDireccion());
		entity.setEventoUnico(dto.getEventoUnico());
		entity.setActivo(Boolean.TRUE);
		entity.setFechaEvento(dto.getFechaEvento());
		
		return entity;
	}
	
	public static EventoUnicoDto entityToDto(Evento entity) {
		if(entity == null) return new EventoUnicoDto();
		
		EventoUnicoDto dto = new EventoUnicoDto();
		dto.setName(entity.getName());
		dto.setDireccion(entity.getDireccion());
		dto.setEventoUnico(entity.getEventoUnico());
		dto.setFechaEvento(entity.getFechaEvento());
				
		return dto;
	}

}
