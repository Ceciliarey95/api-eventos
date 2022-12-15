package com.proyecto.app.service;


import java.util.List;

import com.proyecto.app.dto.OrganizacionDto;
import com.proyecto.app.entity.Organizacion;

public interface IOrganizacionService {

	
	public List<Organizacion> getAll();
	
	public List<Organizacion> findByDeleted(Boolean deleted);
	
	public Organizacion findByName(String name);
		
	public OrganizacionDto save(OrganizacionDto organizacion);
	
	public OrganizacionDto update(OrganizacionDto organizacion);
	
	public OrganizacionDto delete(OrganizacionDto organizacion);

	public Organizacion findByClave(String clave);

	public Organizacion findByCuit(String cuit);

}
