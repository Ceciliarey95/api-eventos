package com.proyecto.app.dto;

import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@SuppressWarnings("deprecation")
public class UsuarioDto {
	
	@NotBlank(message = "El campo nombre no puede estar vacio")
	@Size( max= 20, message = "El nombre no debe ser mayor a 20.")
	private String name;

	@NotBlank(message = "El campo apellido no puede estar vacio")
	@Size(max = 20, message = "El apellido no debe ser mayor a 20.")
	private String lastname;
	
	@NotNull(message="El campo dni no puede ser nulo")
	private Integer dni;
	
	@NotBlank(message = "El campo clave no puede estar vacio")
	@Size(min = 1, max = 6, message = "La clave no debe ser menor a 1 caracter ni mayor a 6.")
	private String clave;

	public UsuarioDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public UsuarioDto(
			@NotBlank(message = "El campo nombre no puede estar vacio") @Size(max = 20, message = "El nombre no debe ser mayor a 20.") String name,
			@NotBlank(message = "El campo apellido no puede estar vacio") @Size(max = 20, message = "El apellido no debe ser mayor a 20.") String lastname,
			@NotNull(message = "El campo dni no puede ser nulo") Integer dni,
			@NotBlank(message = "El campo clave no puede estar vacio") @Size(min = 1, max = 6, message = "La clave no debe ser menor a 1 caracter ni mayor a 6.") String clave) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.dni = dni;
		this.clave = clave;
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

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
}
