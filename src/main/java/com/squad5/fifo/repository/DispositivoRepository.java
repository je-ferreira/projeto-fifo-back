package com.squad5.fifo.repository;

import com.squad5.fifo.model.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {

    Optional<Dispositivo> findByNome(String nome);

    List<Dispositivo> findByAtivoAndAtualNotNull(Boolean ativo);

}
