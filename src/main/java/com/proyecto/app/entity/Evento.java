package com.proyecto.app.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity(name="eventos")
public class Evento implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false, unique = true)
	private Long id;
	
	@Column(length =30)
	private String nombre;
	
	private String direccion;
	private Boolean activo;
	
	@Column(name="fecha_alta")
	private LocalDateTime fechaAlta;
	
	@Column(name="evento_unico")
	private Boolean eventoUnico;
	
	private Date fechaEvento;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "organizacion_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Organizacion organizacion;
	
	
	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Turno> turnos;
	

	public Evento() {
		super();
	}

	public boolean isEventoUnico() {
		return eventoUnico;
	}

	public void setEventoUnico(boolean eventoUnico) {
		this.eventoUnico = eventoUnico;
	}

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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public LocalDateTime getFecha() {
		return fechaAlta;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fechaAlta = fecha;
	}

	public Date getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(Date fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public Evento(Long id, String nombre, String direccion, Boolean activo, LocalDateTime fechaAlta,
			Boolean eventoUnico, Date fechaEvento, Organizacion organizacion, List<Turno> turnos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.activo = activo;
		this.fechaAlta = fechaAlta;
		this.eventoUnico = eventoUnico;
		this.fechaEvento = fechaEvento;
		this.organizacion = organizacion;
		this.turnos = turnos;
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", activo=" + activo
				+ ", fechaAlta=" + fechaAlta + ", eventoUnico=" + eventoUnico + ", fechaEvento=" + fechaEvento
				+ ", organizacion=" + organizacion + ", turnos=" + turnos + "]";
	}

	
}
