package com.squad5.fifo.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity @Data
@Builder
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

	@ManyToMany
	private List<Usuario> usuarioList;

}
