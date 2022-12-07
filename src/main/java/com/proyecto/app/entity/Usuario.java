package com.proyecto.app.entity;


import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Entity(name="usuarios")
@SQLDelete(sql = "UPDATE usuario SET deleted=true WHERE id = ?")
@Where(clause = "deleted = false")
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false, unique = true)
	private Long id;
	
	@Column(length =30)
	private String name;
	
	@Column(length =30)
	private String lastname; 
	private Integer dni;
	private String clave;
	private Boolean deleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", Nombre=" + name + ", Apellido=" + lastname + ", DNI=" + dni + "]";
	}


	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	
	

	public Usuario(Long id, String name, String lastname, Integer dni, String clave, Boolean deleted) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.dni = dni;
		this.clave = clave;
		this.deleted = deleted;
	}

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
