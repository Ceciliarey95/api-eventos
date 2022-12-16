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
		return (List<Organizacion>) organizacionDao.findAll();
	}

	@Override
	public Organizacion findByName(String name) {
		Organizacion organizacion = organizacionDao.findByName(name);
		return organizacion;
	}
	
	@Override
	public OrganizacionDto save(OrganizacionDto organizacionDto) {
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
			entityToPersist.setName(organizacionDto.getName());
			entityToPersist.setClave(organizacionDto.getClave());
			entityToPersist.setEmail(organizacionDto.getEmail());
			entityToPersist.setDireccion(organizacionDto.getDireccion());
			entityToPersist.setDeleted(orgExist.getDeleted());
			entityToPersist.setFechaAlta(orgExist.getFechaAlta());
			
			orgExist = organizacionDao.save(entityToPersist);
			organizacionDto = OrganizacionWrapper.entityToDto(orgExist);
			return organizacionDto;
		}
		return null;
	}

	@Override
	public OrganizacionDto delete(OrganizacionDto organizacionDto) {
		Organizacion orgExist = organizacionDao.findByClave(organizacionDto.getClave());
		if(orgExist != null) {
			Organizacion entityToPersist = new Organizacion();
			
			entityToPersist.setId(orgExist.getId());
			entityToPersist.setCuit(orgExist.getCuit());
			entityToPersist.setName(orgExist.getName());
			entityToPersist.setClave(orgExist.getClave());
			entityToPersist.setEmail(orgExist.getEmail());
			entityToPersist.setDireccion(orgExist.getDireccion());
			entityToPersist.setDeleted(Boolean.TRUE);
			entityToPersist.setFechaAlta(orgExist.getFechaAlta());
			
			orgExist = organizacionDao.save(entityToPersist);
			organizacionDto = OrganizacionWrapper.entityToDto(orgExist);
			return organizacionDto;
		}
		return null;
	}

	public static Logger getLog() {
		return log;
	}

	@Override
	public Organizacion findByCuit(String cuit) {
		Organizacion orgExist = organizacionDao.findByCuit(cuit);
		
		return orgExist;
	}

	@Override
	public Organizacion findByClave(String clave) {
		Organizacion organizacion = organizacionDao.findByClave(clave);
		System.out.println(organizacion);
		return organizacion;
	}

	@Override
	public List<Organizacion> findByDeleted(Boolean deleted) {
		List<Organizacion> organizaciones = organizacionDao.findByDeleted(deleted);
		return organizaciones;
		
	}

	
	
}
