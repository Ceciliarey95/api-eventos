package com.proyecto.app.dto;

import org.hibernate.validator.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@AllArgsConstructor
@SuppressWarnings("deprecation")
public class UsuarioDto {
	
	@NotBlank(message = "El campo nombre no puede estar vacio")
	@Size( max= 20, message = "El nombre no debe ser mayor a 20.")
	private String name;

	@NotBlank(message = "El campo apellido no puede estar vacio")
	@Size(max = 20, message = "El apellido no debe ser mayor a 20.")
	private String lastname;
	
	@NotNull(message="El campo dni no puede ser nulo")
	@Min(value= 7, message="El dni no debe contener menos de 7 caracteres")
	@Max(value=8, message="El dni no debe contener más de 8 caracteres")
	private Integer dni;
	private String clave;
}
