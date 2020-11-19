package com.squad5.fifo.repository;

import com.squad5.fifo.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {

    Optional<Partida> findByNodeId(Long id);

}
