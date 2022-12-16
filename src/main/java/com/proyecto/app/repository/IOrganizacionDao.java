package com.proyecto.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.proyecto.app.entity.Organizacion;

@Repository
public interface IOrganizacionDao extends JpaRepository<Organizacion, Long> {
	
	@Transactional(readOnly = true)
	public Organizacion findByName(String nombre);
	@Transactional(readOnly = true)
	public Organizacion findByCuit(String cuit);
	@Transactional(readOnly = true)
	public Organizacion findByClave(String clave);
	@Transactional(readOnly = true)
	public List<Organizacion> findByDeleted(Boolean deleted);

}
