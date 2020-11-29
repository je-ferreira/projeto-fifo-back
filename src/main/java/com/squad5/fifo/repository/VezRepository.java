package com.squad5.fifo.repository;

import com.squad5.fifo.model.Dispositivo;
import com.squad5.fifo.model.Vez;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface VezRepository extends JpaRepository<Vez, Long> {

    @Query("SELECT DISTINCT v FROM Vez v INNER JOIN Participacao p ON v.id = p.id.vez WHERE p.resultado IS NULL")
    List<Vez> findDistinctVezByResultadoNull();

    @Query("SELECT DISTINCT v FROM Vez v INNER JOIN Participacao p ON v.id = p.id.vez WHERE p.resultado IS NULL AND v.dispositivo = :dispositivo")
    Optional<Vez> findDistinctVezByResultadoNullAndDispositivo(Dispositivo dispositivo);

    Optional<Vez> findByEntradaGreaterThan(Date entrada);

    @Query("SELECT v FROM Vez v WHERE v.dispositivo = :dispositivo AND v.saida IS NULL AND v.entrada IS NOT NULL ORDER BY v.entrada DESC")
    Optional<Vez> findFirstByDispositivo(Dispositivo dispositivo);

}
