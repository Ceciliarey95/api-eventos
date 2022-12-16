package com.proyecto.app.service;

import java.util.List;

import com.proyecto.app.entity.Evento;
import com.proyecto.app.entity.Organizacion;

public interface IEventoService {

	public List<Evento> getAll();
	
	public Evento findByName(String name);
	
	public Evento save(Evento evento);
	
	public Evento update(Evento evento);
	
	public Evento delete(Evento evento);
	
	public List<Evento> findByOrganizacion(Organizacion org);

	public void saveAll(List<Evento> eventos);


}
