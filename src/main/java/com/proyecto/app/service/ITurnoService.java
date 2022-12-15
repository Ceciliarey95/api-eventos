package com.proyecto.app.service;

import java.util.List;
import com.proyecto.app.dto.TurnoEventoRecurrDto;
import com.proyecto.app.dto.TurnoEventoUnicoDto;
import com.proyecto.app.entity.Turno;

public interface ITurnoService {

	public List<Turno> findByActivo(Boolean activo);

	public TurnoEventoRecurrDto saveTurnoEvRe(TurnoEventoRecurrDto turnoEventoRecurrDto);
	
	public TurnoEventoUnicoDto saveEventoAndUsuarioTU(String name, String clave);

	public TurnoEventoUnicoDto saveTurnoEvU(TurnoEventoUnicoDto turnoEventoUnicoDto);
}
