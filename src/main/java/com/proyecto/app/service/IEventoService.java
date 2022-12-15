package com.proyecto.app.service;

import java.util.List;

import com.proyecto.app.dto.EventoUnicoDto;
import com.proyecto.app.entity.Evento;

public interface IEventoService {

	public List<Evento> getAll();
	
	public Evento findByName(String name);
	
	public EventoUnicoDto save(EventoUnicoDto evento);
	
	public EventoUnicoDto update(EventoUnicoDto evento);
	
	public EventoUnicoDto delete(EventoUnicoDto evento);

}
