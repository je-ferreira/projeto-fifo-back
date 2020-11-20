package com.squad5.fifo.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity @Data
@Builder
public class Node {

	@Id
	@GeneratedValue
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date entrada;

	@ManyToOne(optional = false)
	private Metodo metodo;

	@ManyToOne
	private Node superNode;

}
