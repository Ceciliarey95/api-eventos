package com.proyecto.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.app.entity.Evento;
import com.proyecto.app.entity.Organizacion;

@Repository
public interface IEventoDao extends JpaRepository<Evento, Long> {
	@Transactional(readOnly = true)
    public Evento findByName(String name);
	@Transactional(readOnly = true)
	public List<Evento> findByOrganizacion(Organizacion org);	
}
