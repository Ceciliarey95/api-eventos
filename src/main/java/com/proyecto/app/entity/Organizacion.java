package com.proyecto.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;

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
@Entity(name="organizaciones")
public class Organizacion implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false, unique = true)
	private Long id;
	@Column(length =30,unique=true)
	private String name;
	@Column(unique=true)
	private String cuit;
	@Email
	private String email;
	@Column(unique=true)
	private String clave;
	private String direccion;
	private Boolean deleted;
	@Column(name="fecha_alta")
	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date fechaAlta;
	@OneToMany(mappedBy = "organizacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Evento> eventos;

	
	@PrePersist
    public void prePersist() {
        fechaAlta = new Date();
    }
}