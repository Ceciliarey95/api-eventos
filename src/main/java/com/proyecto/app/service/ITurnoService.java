package com.proyecto.app.service;

import java.util.List;
import com.proyecto.app.dto.TurnoEventoRecurrDto;
import com.proyecto.app.dto.TurnoEventoUnicoDto;
import com.proyecto.app.entity.Turno;

public interface ITurnoService {

	public TurnoEventoRecurrDto saveTurnoEvRe(TurnoEventoRecurrDto turnoEventoRecurrDto);
	
	public TurnoEventoRecurrDto saveEventoAndUsuarioTR(String name, String clave);
	
	public TurnoEventoUnicoDto saveEventoAndUsuarioTU(String name, String clave);

	public TurnoEventoUnicoDto saveTurnoEvU(TurnoEventoUnicoDto turnoEventoUnicoDto);

	public List<Turno> findByActivoAndOrganizacion(Boolean activo, String name);

	public List<Turno> findAll();

	public void saveAll(List<Turno> turnos);

}
