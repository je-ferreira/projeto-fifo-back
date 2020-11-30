package com.squad5.fifo.repository;

import com.squad5.fifo.model.Dispositivo;
import com.squad5.fifo.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {

    Optional<Dispositivo> findByNome(String nome);

    @Query("SELECT d FROM Dispositivo d INNER JOIN d.tipoDispositivoList td INNER JOIN td.jogoList j WHERE j = :jogo")
    List<Dispositivo> findByTipoDispositivo(Jogo jogo);

}
