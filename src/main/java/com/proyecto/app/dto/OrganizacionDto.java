package com.proyecto.app.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@AllArgsConstructor
@SuppressWarnings("deprecation")
public class OrganizacionDto {
	
	@NotNull(message = "El campo nombre no puede estar vacio")
	@Size( min=1,max= 20, message = "El nombre no debe ser mayor a 20.")
	private String name;
	@NotNull(message="El campo cuit no puede ser nulo")
	@Min(value= 273919012 ,message="El cuit no puede contener menos de 10 caracteres")
	@Max(value= 00000000000 ,message="El cuit no puede contener más de 11 caracteres")
	private Long cuit;
	@NotBlank(message = "El campo clave no puede estar vacio")
	@Size(min = 20, max = 40, message = "La clave no debe ser menor a 20 caracteres ni mayor a 40.")
	@Column(unique=true)
	private String clave;
	@Email(message = "El email no es valido")
	private String email;
	@NotBlank(message = "La direccion no puede estar vacia")
	private String direccion;
}
