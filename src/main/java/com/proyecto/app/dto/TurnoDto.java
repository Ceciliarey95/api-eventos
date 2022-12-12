package com.proyecto.app.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.proyecto.app.entity.Evento;
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
public class TurnoDto {

	@DateTimeFormat(pattern="dd-MM-yyyy")
	private LocalDateTime fechaHora;
	private Evento evento;
	private Usuario usuario;
	
}
