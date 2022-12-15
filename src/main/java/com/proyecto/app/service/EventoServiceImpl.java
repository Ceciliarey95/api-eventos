package com.proyecto.app.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.app.dto.EventoUnicoDto;
import com.proyecto.app.entity.Evento;
import com.proyecto.app.repository.IEventoDao;
import com.proyecto.app.wrapper.EventoUnicoWrapper;

@Service
public class EventoServiceImpl implements IEventoService {

	private static final Logger log = LoggerFactory.getLogger(EventoServiceImpl.class);

	public static Logger getLog() {
		return log;
	}


	@Autowired
	private IEventoDao eventoDao;
	
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
	public EventoUnicoDto save(EventoUnicoDto eventoUnicoDto) {
		Evento evento = EventoUnicoWrapper.dtoToEntity(eventoUnicoDto);
		evento = eventoDao.save(evento);
		eventoUnicoDto = EventoUnicoWrapper.entityToDto(evento);
		return eventoUnicoDto;
	}

	@Override
	public EventoUnicoDto update(EventoUnicoDto eventoUnicoDto) {
		Evento eventoExist = eventoDao.findByName(eventoUnicoDto.getName());
		if(eventoExist  != null) {
			Evento entityToPersist = new Evento();
			
			entityToPersist.setId(eventoExist.getId());
			entityToPersist.setDireccion(eventoUnicoDto.getDireccion());
			entityToPersist.setName(eventoUnicoDto.getName());
			entityToPersist.setActivo(eventoExist.getActivo());
			entityToPersist.setFechaAlta(eventoExist.getFechaAlta());
			entityToPersist.setEventoUnico(eventoExist.getEventoUnico());
			entityToPersist.setFechaEvento(eventoExist.getFechaEvento());
			eventoExist = eventoDao.save(entityToPersist);
			eventoUnicoDto = EventoUnicoWrapper.entityToDto(eventoExist);
			return eventoUnicoDto;
		}
		return null;
	}

	@Override
	public EventoUnicoDto delete(EventoUnicoDto eventoUnicoDto) {
		Evento eventoExist = eventoDao.findByName(eventoUnicoDto.getName());
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
			eventoUnicoDto = EventoUnicoWrapper.entityToDto(eventoExist);
			return eventoUnicoDto;
		}
		return null;
	}
}
