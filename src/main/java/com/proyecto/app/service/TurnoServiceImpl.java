package com.proyecto.app.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.app.entity.Evento;
import com.proyecto.app.entity.Organizacion;
import com.proyecto.app.entity.Turno;
import com.proyecto.app.repository.IEventoDao;
import com.proyecto.app.repository.IOrganizacionDao;
import com.proyecto.app.repository.ITurnoDao;

@Service
public class TurnoServiceImpl implements ITurnoService {

	private static final Logger log = LoggerFactory.getLogger(TurnoServiceImpl.class);

	@Autowired
	private ITurnoDao turnoDao;
	@Autowired
	private IOrganizacionDao organizacionDao;
	@Autowired
	private IEventoDao eventoDao;
	
	@Override
	public List<Turno> findByActivoAndOrganizacion(Boolean activo, String name) {
		Organizacion org = organizacionDao.findByName(name);
		List<Evento> eventos = eventoDao.findByOrganizacion(org);
		List<Turno> turnosEvento = new ArrayList<Turno>();
		List<Turno> turnosAct = new ArrayList<Turno>();
		for (Evento evento: eventos){
			turnosEvento.add(turnoDao.findByEvento(evento));}	
		for(Turno turnoActivo: turnosEvento) {
			if (turnoActivo.getActivo()== activo) {
				turnosAct.add(turnoActivo);}
		}
		return turnosAct;
	}
	

	@Override
	public Turno save(Turno turno) {
		turno = turnoDao.save(turno);
		return turno;
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
