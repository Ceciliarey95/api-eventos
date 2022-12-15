package com.proyecto.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.app.dto.TurnoEventoRecurrDto;
import com.proyecto.app.dto.TurnoEventoUnicoDto;
import com.proyecto.app.entity.Evento;
import com.proyecto.app.entity.Turno;
import com.proyecto.app.entity.Usuario;
import com.proyecto.app.repository.ITurnoDao;
import com.proyecto.app.wrapper.TurnoEventoWrapper;

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
	public TurnoEventoRecurrDto saveTurnoEvRe(TurnoEventoRecurrDto turnoEventoRecurrDto) {
		Turno turno = TurnoEventoWrapper.dtoToEntityR(turnoEventoRecurrDto);
		turno = turnoDao.save(turno);
		turnoEventoRecurrDto = TurnoEventoWrapper.entityToDtoR(turno);
		return turnoEventoRecurrDto;
	}
	@Override
	public TurnoEventoUnicoDto saveTurnoEvU(TurnoEventoUnicoDto turnoEventoUnicoDto) {
		Turno turno = TurnoEventoWrapper.dtoToEntityU(turnoEventoUnicoDto);
		turno = turnoDao.save(turno);
		turnoEventoUnicoDto = TurnoEventoWrapper.entityToDtoU(turno);
		return turnoEventoUnicoDto;
	}
	
	public TurnoEventoUnicoDto saveEventoAndUsuarioTU(String name, String clave) {
		TurnoEventoUnicoDto turnoEventoUnicoDto= new TurnoEventoUnicoDto();
		Usuario usuario = usuarioService.findByClave(clave);
		Evento evento = eventoService.findByName(name);

		if(evento != null) {
			Turno turnoEventoUnico = TurnoEventoWrapper.dtoToEntityU(turnoEventoUnicoDto);
			turnoEventoUnico.setEvento(evento);
			if(evento.getEventoUnico()) {
				turnoEventoUnico.setFechaHora(evento.getFechaEvento());
		}
		
		}
		if(usuario != null) {
			turnoEventoUnicoDto.setUsuario(usuario);
		}
		Turno turno = TurnoEventoWrapper.dtoToEntityU(turnoEventoUnicoDto);
		turno = turnoDao.save(turno);
		return turnoEventoUnicoDto;
	}
	
	public static Logger getLog() {
		return log;
	}

}
