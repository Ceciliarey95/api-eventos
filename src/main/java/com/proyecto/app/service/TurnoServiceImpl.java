package com.proyecto.app.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.app.dto.TurnoEventoRecurrDto;
import com.proyecto.app.dto.TurnoEventoUnicoDto;
import com.proyecto.app.entity.Evento;
import com.proyecto.app.entity.Organizacion;
import com.proyecto.app.entity.Turno;
import com.proyecto.app.entity.Usuario;
import com.proyecto.app.repository.IEventoDao;
import com.proyecto.app.repository.IOrganizacionDao;
import com.proyecto.app.repository.ITurnoDao;
import com.proyecto.app.wrapper.TurnoEventoWrapper;

@Service
public class TurnoServiceImpl implements ITurnoService {

	private static final Logger log = LoggerFactory.getLogger(TurnoServiceImpl.class);

	@Autowired
	private ITurnoDao turnoDao;
	@Autowired
	private IOrganizacionDao organizacionDao;
	@Autowired
	private IEventoDao eventoDao;
	@Autowired
	private IEventoService eventoService;
	@Autowired
	private IUsuarioService usuarioService;
	
	@Override
	public List<Turno> findByActivoAndOrganizacion(Boolean activo, String name) {
		Organizacion org = organizacionDao.findByName(name);
		Long org_id =org.getId();
		List<Evento> eventos = eventoDao.findByOrganizacion(org_id);
		List<Turno> turnosEvento = new ArrayList<Turno>();
		List<Turno> turnosAct = new ArrayList<Turno>();
		for (Evento evento: eventos){
			Long eventoId = evento.getId();
			turnosEvento.add(turnoDao.findByEvento(eventoId));	
			for(Turno turnoActivo: turnosEvento) {
				if (turnoActivo.getActivo()== activo) {
					turnosAct.add(turnoActivo);
				}
			}
			}
		
		return turnosAct ;
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
	
	public TurnoEventoRecurrDto saveEventoAndUsuarioTR(String name, String clave) {
		TurnoEventoRecurrDto turnoEventoRecurrDto= new TurnoEventoRecurrDto();
		Usuario usuario = usuarioService.findByClave(clave);
		Evento evento = eventoService.findByName(name);

		if(evento != null) {
			Turno turnoEventoRecurr = TurnoEventoWrapper.dtoToEntityR(turnoEventoRecurrDto);
			turnoEventoRecurr.setEvento(evento);
			if(evento.getEventoUnico()) {
				turnoEventoRecurr.setFechaHora(evento.getFechaEvento());
		}
		
		}
		if(usuario != null) {
			Turno turno = TurnoEventoWrapper.dtoToEntityR(turnoEventoRecurrDto);
			turno.setUsuario(usuario);
			turno = turnoDao.save(turno);

		}
		return turnoEventoRecurrDto;
	}
	public static Logger getLog() {
		return log;
	}


	@Override
	public List<Turno> findAll() {
		List<Turno> turnos = turnoDao.findAll();
		return turnos;
	}


	@Override
	public void saveAll(List<Turno> turnos) {
		for(Turno turno: turnos) {
			turno= turnoDao.save(turno);
		}
	}

}
