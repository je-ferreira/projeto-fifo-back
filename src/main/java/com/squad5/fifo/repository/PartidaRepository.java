package com.squad5.fifo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

 
import com.squad5.fifo.model.Partida;

public interface PartidaRepository extends JpaRepository<Partida, Long>  {

}
