package com.proyecto.app.service;

import java.util.List;

import com.proyecto.app.entity.Evento;

public interface IEventoService {

	public List<Evento> getAll();
	
	public Evento findByName(String name);
	
	public Evento save(Evento evento);
	
	public Evento update(Evento evento);
	
	public Evento delete(Evento evento);
	
	public List<Evento> findByOrganizacion(Long id);

	public void saveAll(List<Evento> eventos);

}
