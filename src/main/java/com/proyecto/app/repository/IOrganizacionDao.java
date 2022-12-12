package com.proyecto.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.app.entity.Organizacion;

@Repository
public interface IOrganizacionDao extends JpaRepository<Organizacion, Long> {
	
	public Organizacion findByName(String nombre);

	public Organizacion findByCuit(Long cuit);

	public Organizacion findByClave(String clave);

	public List<Organizacion> findByDeleted(Boolean deleted);
}
