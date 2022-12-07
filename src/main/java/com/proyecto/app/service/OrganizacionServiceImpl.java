package com.proyecto.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.app.dto.OrganizacionDto;
import com.proyecto.app.entity.Organizacion;

import com.proyecto.app.repository.IOrganizacionDao;
import com.proyecto.app.wrapper.OrganizacionWrapper;


@Service
public class OrganizacionServiceImpl implements IOrganizacionService {

	private static final Logger log = LoggerFactory.getLogger(OrganizacionServiceImpl.class);
	
	@Autowired
	private IOrganizacionDao organizacionDao;

	@Override
	public List<Organizacion> getAll() {
		// TODO Auto-generated method stub
		return organizacionDao.findAll();
	}

	@Override
	public Organizacion findByNombre(String nombre) {
		Organizacion organizacion = organizacionDao.findByNombre(nombre);
		return organizacion;
	}

	@Override
	public Organizacion findByCuit(Integer cuit) {
		Organizacion organizacion = organizacionDao.findByCuit(cuit);
		return organizacion;
	}

	@Override
	public OrganizacionDto save(OrganizacionDto organizacionDto ) {
		Organizacion organizacion = OrganizacionWrapper.dtoToEntity(organizacionDto);
		organizacion = organizacionDao.save(organizacion);
		organizacionDto = OrganizacionWrapper.entityToDto(organizacion);
		return organizacionDto;
	}

	@Override
	public OrganizacionDto update(OrganizacionDto organizacionDto) {
		Organizacion orgExist = organizacionDao.findByCuit(organizacionDto.getCuit());
		if(orgExist != null) {
			Organizacion entityToPersist = new Organizacion();
			
			entityToPersist.setId(orgExist.getId());
			entityToPersist.setCuit(organizacionDto.getCuit());
			entityToPersist.setNombre(organizacionDto.getNombre());
			
			orgExist = organizacionDao.save(entityToPersist);
			organizacionDto = OrganizacionWrapper.entityToDto(orgExist);
			return organizacionDto;
		}
		return null;
	}

	@Override
	public OrganizacionDto delete(OrganizacionDto organizacionDto) {
		Organizacion organizacionExist = organizacionDao.findByCuit(organizacionDto.getCuit());
		if(organizacionExist != null) {
			Organizacion entityToPersist = new Organizacion();
			
			entityToPersist.setDeleted(true);
			
			organizacionExist = organizacionDao.save(entityToPersist);
			organizacionDto = OrganizacionWrapper.entityToDto(organizacionExist);
			return organizacionDto;
		}
		return null;
	}

	public static Logger getLog() {
		return log;
	}
	
	
}
