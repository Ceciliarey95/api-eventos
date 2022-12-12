package com.proyecto.app.service;


import java.util.List;

import com.proyecto.app.dto.OrganizacionDto;
import com.proyecto.app.entity.Organizacion;

public interface IOrganizacionService {

	
	public List<Organizacion> getAll();
	
	public Organizacion findByNombre(String nombre);
	
	public Organizacion findByCuit(Long cuit);
	
	public OrganizacionDto save(OrganizacionDto organizacion);
	
	public OrganizacionDto update(OrganizacionDto organizacion);
	
	public OrganizacionDto delete(OrganizacionDto organizacion);

}
