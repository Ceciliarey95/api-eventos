package com.proyecto.app.service;

import java.util.List;
import com.proyecto.app.dto.TurnoDto;
import com.proyecto.app.entity.Turno;

public interface ITurnoService {

	public List<Turno> findByActivo(Boolean activo);

	public TurnoDto save(TurnoDto turnoDto);
	
	public TurnoDto saveEventoAndUsuario(String name, String clave);
}
