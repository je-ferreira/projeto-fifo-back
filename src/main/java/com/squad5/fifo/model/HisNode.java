package com.squad5.fifo.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity @Data
public class HisNode {

	@Id
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date entrada;

	@ManyToOne(optional = false)
	private HisMetodo hisMetodo;

	@ManyToOne
	private HisNode superHisNode;

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Usuario> usuarioList;

}
