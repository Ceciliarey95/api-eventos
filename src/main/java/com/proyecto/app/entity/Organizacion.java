package com.proyecto.app.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;



@Entity(name="organizaciones")
@SQLDelete(sql = "UPDATE organizacion SET deleted=true WHERE id = ?")
@Where(clause = "deleted = false")
public class Organizacion implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false, unique = true)
	private Long id;
	
	@Column(length =30)
	private String nombre;
	private Integer cuit;
	private String email;
	private String clave;
	private String direccion;
	private Boolean deleted;
	@Column(name="fecha_alta")
	private LocalDateTime fechaAlta;
	
	@OneToMany(mappedBy = "organizacion", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Evento> eventos;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCuit() {
		return cuit;
	}

	public void setCuit(Integer cuit) {
		this.cuit = cuit;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public List<Evento> getEvents() {
		return eventos;
	}

	public void setEvents(List<Evento> events) {
		this.eventos = events;
	}

	public Organizacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Organizacion(Long id, String nombre, Integer cuit, String email, String clave, String direccion,
			Boolean deleted, LocalDateTime fechaAlta, List<Evento> eventos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cuit = cuit;
		this.email = email;
		this.clave = clave;
		this.direccion = direccion;
		this.deleted = deleted;
		this.fechaAlta = fechaAlta;
		this.eventos = eventos;
	}

	@Override
	public String toString() {
		return "Organizacion [id=" + id + ", nombre=" + nombre + ", cuit=" + cuit + ", email=" + email + ", clave="
				+ clave + ", direccion=" + direccion + ", deleted=" + deleted + ", fechaAlta=" + fechaAlta + ", eventos="
				+ eventos + "]";
	}
	
}