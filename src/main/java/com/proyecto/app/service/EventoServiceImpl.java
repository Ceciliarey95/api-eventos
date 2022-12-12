package com.proyecto.app.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.app.dto.EventoDto;
import com.proyecto.app.entity.Evento;
import com.proyecto.app.repository.IEventoDao;
import com.proyecto.app.wrapper.EventoWrapper;

@Service
public class EventoServiceImpl implements IEventoService {

	private static final Logger log = LoggerFactory.getLogger(OrganizacionServiceImpl.class);

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
	public EventoDto save(EventoDto eventoDto) {
		Evento evento = EventoWrapper.dtoToEntity(eventoDto);
		evento = eventoDao.save(evento);
		eventoDto = EventoWrapper.entityToDto(evento);
		return eventoDto;
	}

	@Override
	public EventoDto update(EventoDto eventoDto) {
		Evento eventoExist = eventoDao.findByName(eventoDto.getName());
		if(eventoExist  != null) {
			Evento entityToPersist = new Evento();
			
			entityToPersist.setId(eventoExist.getId());
			entityToPersist.setDireccion(eventoDto.getDireccion());
			entityToPersist.setName(eventoDto.getName());
			entityToPersist.setActivo(eventoExist.getActivo());
			entityToPersist.setFechaAlta(eventoExist.getFechaAlta());
			entityToPersist.setEventoUnico(eventoExist.getEventoUnico());
			entityToPersist.setFechaEvento(eventoExist.getFechaEvento());
			entityToPersist.setOrganizacion(eventoExist.getOrganizacion());
			entityToPersist.setTurnos(eventoExist.getTurnos());
			
			eventoExist = eventoDao.save(entityToPersist);
			eventoDto = EventoWrapper.entityToDto(eventoExist);
			return eventoDto;
		}
		return null;
	}

	@Override
	public EventoDto delete(EventoDto eventoDto) {
		Evento eventoExist = eventoDao.findByName(eventoDto.getName());
		if(eventoExist  != null) {
			Evento entityToPersist = new Evento();
			entityToPersist.setId(eventoExist.getId());
			entityToPersist.setDireccion(eventoExist.getDireccion());
			entityToPersist.setName(eventoExist.getName());
			entityToPersist.setActivo(Boolean.FALSE);
			entityToPersist.setFechaAlta(eventoExist.getFechaAlta());
			entityToPersist.setEventoUnico(eventoExist.getEventoUnico());
			entityToPersist.setFechaEvento(eventoExist.getFechaEvento());
			entityToPersist.setOrganizacion(eventoExist.getOrganizacion());
			entityToPersist.setTurnos(eventoExist.getTurnos());
			
			eventoExist = eventoDao.save(entityToPersist);
			eventoDto = EventoWrapper.entityToDto(eventoExist);
			return eventoDto;
		}
		return null;
	}
}
