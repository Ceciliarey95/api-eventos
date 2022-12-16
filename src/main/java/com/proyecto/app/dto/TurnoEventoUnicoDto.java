package com.proyecto.app.dto;

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
	
	private String clave;
	private String nameEvento;
}
