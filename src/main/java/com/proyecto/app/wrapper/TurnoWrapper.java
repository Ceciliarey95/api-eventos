package com.proyecto.app.wrapper;
import com.proyecto.app.dto.TurnoDto;
import com.proyecto.app.entity.Turno;

public class TurnoWrapper {

	public static Turno dtoToEntity(TurnoDto dto) {
		if(dto == null) return new Turno();
		
		Turno entity = new Turno();
		entity.setActivo(Boolean.TRUE);
		entity.setFechaHora(dto.getFechaHora());
		entity.setEvento(dto.getEvento());
		entity.setUsuario(dto.getUsuario());
		entity.setCodigo(entity.getCodigo());
		return entity;
	}
	
	public static TurnoDto entityToDto(Turno entity) {
		if(entity == null) return new TurnoDto();
		
		TurnoDto dto = new TurnoDto();
		dto.setFechaHora(entity.getFechaHora());
		dto.setEvento(entity.getEvento());
		dto.setUsuario(entity.getUsuario());
				
		return dto;
	}
}
