package com.proyecto.app.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.app.dto.EventoUnicoDto;
import com.proyecto.app.entity.Evento;
import com.proyecto.app.repository.IEventoDao;
import com.proyecto.app.wrapper.EventoWrapper;

@Service
public class EventoServiceImpl implements IEventoService {

	private static final Logger log = LoggerFactory.getLogger(EventoServiceImpl.class);

	@Autowired
	private IEventoDao eventoDao;
	
	public static Logger getLog() {
		return log;
	}

	
	@Override
	public List<Evento> getAll() {
		return (List<Evento>) eventoDao.findAll();
	}

	@Override
	public Evento findByName(String name) {
		Evento evento = eventoDao.findByName(name);
		return evento;
	}

	@Override
	public Evento save(Evento evento) {
		evento = eventoDao.save(evento);
		return evento;
	}

	@Override
	public Evento update(Evento evento) {
		Evento eventoExist = eventoDao.findByName(evento.getName());
		if(eventoExist  != null) {
			EventoUnicoDto eventoUnicoDto = EventoWrapper.entityToDtoU(eventoExist);
			Evento entityToPersist = new Evento();
			
			entityToPersist.setId(eventoExist.getId());
			entityToPersist.setDireccion(eventoUnicoDto.getDireccion());
			entityToPersist.setName(eventoUnicoDto.getName());
			entityToPersist.setActivo(eventoExist.getActivo());
			entityToPersist.setFechaAlta(eventoExist.getFechaAlta());
			entityToPersist.setEventoUnico(eventoExist.getEventoUnico());
			entityToPersist.setFechaEvento(eventoUnicoDto.getFechaEvento());
			eventoExist = eventoDao.save(entityToPersist);
			return eventoExist;
		}
		return null;
	}

	@Override
	public Evento delete(Evento evento) {
		Evento eventoExist = eventoDao.findByName(evento.getName());
		if(eventoExist  != null) {
			Evento entityToPersist = new Evento();
			entityToPersist.setId(eventoExist.getId());
			entityToPersist.setDireccion(eventoExist.getDireccion());
			entityToPersist.setName(eventoExist.getName());
			entityToPersist.setActivo(Boolean.FALSE);
			entityToPersist.setFechaAlta(eventoExist.getFechaAlta());
			entityToPersist.setEventoUnico(eventoExist.getEventoUnico());
			entityToPersist.setFechaEvento(eventoExist.getFechaEvento());			
			eventoExist = eventoDao.save(entityToPersist);
			return evento;
		}
		return null;
	}
		
	@Override
	public List<Evento> findByOrganizacion(Long id){
		List<Evento> eventos = eventoDao.findByOrganizacion(id);
		return eventos;
	}


	@Override
	public void saveAll(List<Evento> eventos) {
		for (Evento evento : eventos) {
			evento= eventoDao.save(evento);
		}
	}
}
