package com.squad5.fifo.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity @Data
@Builder @AllArgsConstructor
@NoArgsConstructor
public class Vez {

	@Id
	@GeneratedValue
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date entrada;

	@ManyToOne(optional = false)
	private Jogo jogo;

	@ManyToOne
	private Dispositivo dispositivo;

	@ManyToOne
	private Usuario convidante;

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@ToString.Exclude
	private List<Usuario> usuarioList;

}
