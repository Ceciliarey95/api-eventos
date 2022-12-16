package com.proyecto.app.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name="eventos")
public class Evento implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false, unique = true)
	private Long id;
	@Column(unique=true)
	private String name;
	private String direccion;
	private Boolean activo;
	@Column(name="fecha_alta")
	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date fechaAlta;
	@Column(name="evento_unico")
	private Boolean eventoUnico;
	@Column(unique=true)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private LocalDateTime fechaEvento;
	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Turno> turnos;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "organizacion_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Organizacion organizacion;
	


	@PrePersist
    public void prePersist() {
        fechaAlta = new Date();
    }



	@Override
	public String toString() {
		return "Evento [id=" + id + ", name=" + name + ", direccion=" + direccion + ", activo=" + activo
				+ ", fechaAlta=" + fechaAlta + ", eventoUnico=" + eventoUnico + ", fechaEvento=" + fechaEvento
				+ "]";
	}
}
