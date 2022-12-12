package com.proyecto.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.app.entity.Organizacion;

@Repository
public interface IOrganizacionDao extends JpaRepository<Organizacion, Long> {
	
	public Organizacion findByNombre(String nombre);

	public Organizacion findByCuit(Long cuit);
}
