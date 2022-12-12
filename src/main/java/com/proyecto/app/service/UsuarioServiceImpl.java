package com.proyecto.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.app.dto.UsuarioDto;
import com.proyecto.app.entity.Usuario;
import com.proyecto.app.repository.IUsuarioDao;
import com.proyecto.app.wrapper.UsuarioWrapper;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	public List<Usuario> getAll() {
		// TODO Auto-generated method stub
		return usuarioDao.findAll();
	}

	@Override
	public Usuario findById(Long id) {
		// TODO Auto-generated method stub
		return usuarioDao.findById(id).orElse(new Usuario());
	}

	@Override
	public UsuarioDto save(UsuarioDto usuarioDto) {
		Usuario usuario = UsuarioWrapper.dtoToEntity(usuarioDto);
		usuario = usuarioDao.save(usuario);
		usuarioDto = UsuarioWrapper.entityToDto(usuario);
		return usuarioDto;
	}

	@Override
	public List<Usuario> findByLastname(String lastname) {
		// TODO Auto-generated method stub
		List<Usuario> usuarios = usuarioDao.findByLastname(lastname);
		return usuarios;
	}

	@Override
	public UsuarioDto findByDni(Long dni) {
		Usuario usuario = usuarioDao.findByDni(dni);
		return UsuarioWrapper.entityToDto(usuario);
	}
	@Override
	public Usuario findByClave(String clave) {
		Usuario usuario = usuarioDao.findByClave(clave);
		return usuario;
	}

	@Override
	public UsuarioDto update(UsuarioDto usuarioDto) {
		Usuario usuarioExist = usuarioDao.findByDni(usuarioDto.getDni());
		if(usuarioExist != null) {
			Usuario entityToPersist = new Usuario();
			
			entityToPersist.setId(usuarioExist.getId());
			entityToPersist.setDni(usuarioDto.getDni());
			entityToPersist.setLastname(usuarioDto.getLastname());
			entityToPersist.setName(usuarioDto.getName());
			
			usuarioExist = usuarioDao.save(entityToPersist);
			usuarioDto = UsuarioWrapper.entityToDto(usuarioExist);
			return usuarioDto;
		}
		return null;
	}
	
	@Override
	public UsuarioDto delete(UsuarioDto usuarioDto) {
		Usuario usuarioExist = usuarioDao.findByDni(usuarioDto.getDni());
		if(usuarioExist != null) {
			Usuario entityToPersist = new Usuario();
			
			entityToPersist.setDeleted(true);
			
			usuarioExist = usuarioDao.save(entityToPersist);
			usuarioDto = UsuarioWrapper.entityToDto(usuarioExist);
			return usuarioDto;
		}
		return null;
	}

	public static Logger getLog() {
		return log;
	}

}
