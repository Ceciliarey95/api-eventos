package com.proyecto.app.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
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
@Entity(name="usuarios")
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false, unique = true)
	private Long id;
	@Column(length =30)
	private String name;
	@Column(length =30)
	private String lastname; 
	@Column(unique=true)
	private Long dni;
	@Column(unique=true)
	private String clave;
	private Boolean deleted;
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Turno> turnos;
}
