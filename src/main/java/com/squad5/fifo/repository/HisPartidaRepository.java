package com.squad5.fifo.repository;

import com.squad5.fifo.model.HisPartida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HisPartidaRepository extends JpaRepository<HisPartida, Long> {

}
