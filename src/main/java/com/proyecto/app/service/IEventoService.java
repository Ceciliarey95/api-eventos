package com.proyecto.app.service;

import java.util.List;

import com.proyecto.app.dto.EventoDto;
import com.proyecto.app.entity.Evento;

public interface IEventoService {

	public List<Evento> getAll();
	
	public Evento findByName(String name);
	
	public EventoDto save(EventoDto evento);
	
	public EventoDto update(EventoDto evento);
	
	public EventoDto delete(EventoDto evento);

}
