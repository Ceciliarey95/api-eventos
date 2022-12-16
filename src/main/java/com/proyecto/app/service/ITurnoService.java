package com.proyecto.app.service;

import java.util.List;
import com.proyecto.app.entity.Turno;

public interface ITurnoService {

	public List<Turno> findByActivoAndOrganizacion(Boolean activo, String name);

	public List<Turno> findAll();

	public void saveAll(List<Turno> turnos);

	public Turno save(Turno turno);

}
