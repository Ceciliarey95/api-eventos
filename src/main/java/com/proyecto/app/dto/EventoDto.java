package com.proyecto.app.dto;

import java.time.LocalDate;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

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
public class EventoDto {
	
	@NotBlank(message = "El campo nombre no puede estar vacio")
	@Size( max= 20, message = "El nombre no debe ser mayor a 20.")
	private String name;
	private String direccion;
	private Boolean eventoUnico;
	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd-MM-yyyy")
	private LocalDate fechaEvento;
}
