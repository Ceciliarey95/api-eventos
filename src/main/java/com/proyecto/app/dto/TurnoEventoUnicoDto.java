package com.proyecto.app.dto;

import javax.validation.constraints.NotEmpty;

import com.proyecto.app.entity.Usuario;

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
public class TurnoEventoUnicoDto {
	
	@NotEmpty(message="El usuario no puede estar vacio")
	private Usuario usuario;
	
}
