package com.squad5.fifo.repository;

import com.squad5.fifo.model.Metodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoRepository extends JpaRepository<Metodo, Long> {

}
