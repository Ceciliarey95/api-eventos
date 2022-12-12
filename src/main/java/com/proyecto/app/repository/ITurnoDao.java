package com.proyecto.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.app.entity.Turno;

public interface ITurnoDao extends JpaRepository<Turno, Long>  {

	public List<Turno> findByActivo(Boolean activo);

}
