package com.proyecto.app.wrapper;

import com.proyecto.app.dto.EventoRecurrDto;
import com.proyecto.app.dto.EventoUnicoDto;
import com.proyecto.app.entity.Evento;

public class EventoWrapper {
	
	public static Evento dtoToEntityU(EventoUnicoDto dto) {
		if(dto == null) return new Evento();
		
		Evento entity = new Evento();
		entity.setName(dto.getName());
		entity.setDireccion(dto.getDireccion());
		entity.setEventoUnico(dto.getEventoUnico());
		entity.setActivo(Boolean.TRUE);
		entity.setFechaEvento(dto.getFechaEvento());
		
		return entity;
	}
	
	public static EventoUnicoDto entityToDtoU(Evento entity) {
		if(entity == null) return new EventoUnicoDto();
		
		EventoUnicoDto dto = new EventoUnicoDto();
		dto.setName(entity.getName());
		dto.setDireccion(entity.getDireccion());
		dto.setEventoUnico(entity.getEventoUnico());
		dto.setFechaEvento(entity.getFechaEvento());
				
		return dto;
	}
	public static Evento dtoToEntityR(EventoRecurrDto dto) {
		if(dto == null) return new Evento();
		
		Evento entity = new Evento();
		entity.setName(dto.getName());
		entity.setDireccion(dto.getDireccion());
		entity.setEventoUnico(Boolean.FALSE);
		entity.setActivo(Boolean.TRUE);
		
		return entity;
	}
	
	public static EventoRecurrDto entityToDtoR(Evento entity) {
		if(entity == null) return new EventoRecurrDto();
		
		EventoRecurrDto dto = new EventoRecurrDto();
		dto.setName(entity.getName());
		dto.setDireccion(entity.getDireccion());
				
		return dto;
	}

}
