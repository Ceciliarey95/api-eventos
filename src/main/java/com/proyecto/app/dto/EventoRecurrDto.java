package com.proyecto.app.dto;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("deprecation")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@AllArgsConstructor
public class EventoRecurrDto {

	
	@NotBlank(message = "El campo nombre no puede estar vacio")
	@Size( max= 20, message = "El nombre no debe ser mayor a 20.")
	private String name;
	@NotBlank(message = "El campo direccion no puede estar vacio")
	private String direccion;

}
