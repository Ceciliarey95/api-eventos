package com.proyecto.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("deprecation")
public class OrganizacionDto {
	
	@NotBlank(message = "El campo nombre no puede estar vacio")
	@Size( max= 20, message = "El nombre no debe ser mayor a 20.")
	private String nombre;
	
	@NotNull(message="El campo cuit no puede ser nulo")
	private Integer cuit;
	
	@NotBlank(message = "El campo clave no puede estar vacio")
	@Size(min = 20, max = 40, message = "La clave no debe ser menor a 20 caracteres ni mayor a 40.")
	private String clave;

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

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public OrganizacionDto(
			@NotBlank(message = "El campo nombre no puede estar vacio") @Size(max = 20, message = "El nombre no debe ser mayor a 20.") String nombre,
			@NotNull(message = "El campo cuit no puede ser nulo") Integer cuit,
			@NotBlank(message = "El campo clave no puede estar vacio") @Size(min = 20, max = 40, message = "La clave no debe ser menor a 20 caracteres ni mayor a 40.") String clave) {
		super();
		this.nombre = nombre;
		this.cuit = cuit;
		this.clave = clave;
	}

	
	
	
	@Override
	public String toString() {
		return "OrganizacionDto [nombre=" + nombre + ", cuit=" + cuit + ", clave=" + clave + "]";
	}

	public OrganizacionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
