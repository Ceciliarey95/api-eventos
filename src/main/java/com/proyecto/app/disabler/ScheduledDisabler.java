package com.proyecto.app.disabler;

import java.util.Date;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.proyecto.app.entity.Evento;
import com.proyecto.app.entity.Turno;
import com.proyecto.app.service.IEventoService;
import com.proyecto.app.service.ITurnoService;

@Component
@EnableScheduling
public class ScheduledDisabler {

private Logger log = LoggerFactory.getLogger(ScheduledDisabler.class);
	
	@Autowired
	private ITurnoService turnoService;
	@Autowired
	private IEventoService eventoService;
	//private static final String TIME_ZONE =  "America/Buenos_Aires";   
	
	@SuppressWarnings("deprecation")
	@Scheduled(cron = "0 0 0 * * ?")
	public void disableTurnos() {
		Date today = new Date();		
		today = new Date(today.getYear(), today.getMonth(), (today.getDate()));		
		List<Turno> turnos = turnoService.findAll();		
		turnos.forEach(e -> e.setActivo(!e.getActivo()));
		turnoService.saveAll(turnos);
		log.info("Se deshabilitaron los turnos que ya han pasado.");
	}
	
	@SuppressWarnings("deprecation")
	@Scheduled(cron = "0 0 0 * * ?")
	public void disableEventos() {
		Date today = new Date();		
		today = new Date(today.getYear(), today.getMonth(), (today.getDate()));		
		List<Evento> eventos = eventoService.getAll();
		eventos.forEach(e -> e.setActivo(!e.getActivo()));
		eventoService.saveAll(eventos);
		log.info("Se deshabilitaron los eventos que ya han pasado.");
	}
}
