package com.proyecto.app.wrapper;

import com.proyecto.app.dto.TurnoEventoRecurrDto;
import com.proyecto.app.dto.TurnoEventoUnicoDto;
import com.proyecto.app.entity.Evento;
import com.proyecto.app.entity.Turno;


public class TurnoEventoWrapper {

	public static Turno dtoToEntityR(TurnoEventoRecurrDto dto) {
		if(dto == null) return new Turno();
		
		Turno entity = new Turno();
		entity.setActivo(Boolean.TRUE);
		entity.setFechaHora(dto.getFechaHora());
		entity.setCodigo(entity.getCodigo());
		entity.setEvento(entity.getEvento());
		entity.setUsuario(entity.getUsuario());

		return entity;
	}
	
	public static Turno dtoToEntityU(TurnoEventoUnicoDto dto) {
		if(dto == null) return new Turno();

		Turno entity = new Turno();
		entity.setActivo(Boolean.TRUE);
		entity.setUsuario(entity.getUsuario());
		entity.setCodigo(entity.getCodigo());
		entity.setEvento(entity.getEvento());
		entity.setUsuario(entity.getUsuario());
		return entity;
	}
	

	public static TurnoEventoRecurrDto entityToDtoR(Turno entity) {
		if(entity == null) return new TurnoEventoRecurrDto();
		
		TurnoEventoRecurrDto dto = new TurnoEventoRecurrDto();
		
		dto.setFechaHora(entity.getFechaHora());
		dto.setClave(entity.getUsuario().getClave());
		dto.setNameEvento(entity.getEvento().getName());
				
		return dto;
	}
	
	public static TurnoEventoUnicoDto entityToDtoU(Turno entity) {
		if(entity == null) return new TurnoEventoUnicoDto();
		
		TurnoEventoUnicoDto dto = new TurnoEventoUnicoDto();
		
		dto.setClave(entity.getUsuario().getClave());
		dto.setNameEvento(entity.getEvento().getName());
				
		return dto;
	}
}
