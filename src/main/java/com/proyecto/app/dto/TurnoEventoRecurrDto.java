package com.proyecto.app.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
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
public class TurnoEventoRecurrDto {

	@Future
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private LocalDateTime fechaHora;
	@NotEmpty(message="El usuario no puede estar vacio")
	private UsuarioDto usuarioDto;
	
}
