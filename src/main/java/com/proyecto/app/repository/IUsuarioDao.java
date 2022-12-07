package com.proyecto.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.app.entity.Usuario;


@Repository
public interface IUsuarioDao extends JpaRepository<Usuario, Long>{

	public Usuario findByDni(Integer dni);
	
	public List<Usuario> findByLastname(String lastname);

}
