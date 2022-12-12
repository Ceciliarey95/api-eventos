package com.proyecto.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.app.dto.TurnoDto;
import com.proyecto.app.entity.Evento;
import com.proyecto.app.entity.Turno;
import com.proyecto.app.entity.Usuario;
import com.proyecto.app.repository.ITurnoDao;
import com.proyecto.app.wrapper.TurnoWrapper;

@Service
public class TurnoServiceImpl implements ITurnoService {

	private static final Logger log = LoggerFactory.getLogger(TurnoServiceImpl.class);

	@Autowired
	private ITurnoDao turnoDao;
	
	@Autowired
	private IEventoService eventoService;
	@Autowired
	private IUsuarioService usuarioService;
	
	@Override
	public List<Turno> findByActivo(Boolean activo) {
		List<Turno> turnos = turnoDao.findByActivo(activo);
		return turnos;
	}

	@Override
	public TurnoDto save(TurnoDto turnoDto) {
		Turno turno = TurnoWrapper.dtoToEntity(turnoDto);
		turno = turnoDao.save(turno);
		turnoDto = TurnoWrapper.entityToDto(turno);
		return turnoDto;
	}
	
	public TurnoDto saveEventoAndUsuario(String name, String clave) {
		TurnoDto turnoDto= new TurnoDto();
		Usuario usuario = usuarioService.findByClave(clave);
		Evento evento = eventoService.findByName(name);

		if(evento != null) {
			turnoDto.setEvento(evento);
			if(evento.getEventoUnico()) {
				turnoDto.setFechaHora(evento.getFechaEvento());
		}
		
		}
		if(usuario != null) {
			turnoDto.setUsuario(usuario);
		}
		Turno turno = TurnoWrapper.dtoToEntity(turnoDto);
		turno = turnoDao.save(turno);
		return turnoDto;
	}
	
	public static Logger getLog() {
		return log;
	}

}
