package com.squad5.fifo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.squad5.fifo.model.CargoUsuario;
import com.squad5.fifo.model.HisMetodo;

@Repository
public interface HisMetodoRepository extends JpaRepository<HisMetodo, Long> {

}
