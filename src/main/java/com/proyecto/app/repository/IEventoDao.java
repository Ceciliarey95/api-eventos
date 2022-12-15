package com.proyecto.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.app.entity.Evento;

@Repository
public interface IEventoDao extends JpaRepository<Evento, Long> {

    public Evento findByName(String name);

	public List<Evento> findByOrganizacion(Long organizacion_id);
}
