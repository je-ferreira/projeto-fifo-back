package com.squad5.fifo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.squad5.fifo.model.CargoUsuario;
import com.squad5.fifo.model.HisNode;

public interface HisNodeRepository extends JpaRepository<HisNode, Long> {

}
