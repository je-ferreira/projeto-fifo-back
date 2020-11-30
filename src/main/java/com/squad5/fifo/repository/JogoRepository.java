package com.squad5.fifo.repository;

import java.util.List;
import java.util.Optional;

import com.squad5.fifo.model.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.squad5.fifo.model.Jogo;
import org.springframework.data.jpa.repository.Query;

public interface JogoRepository extends JpaRepository<Jogo, Long> {

	Optional<Jogo> findByNome(String nome);

	List<Jogo> findByAtivo(Boolean ativo);

	@Query("SELECT DISTINCT v.jogo FROM Participacao p INNER JOIN Vez v ON p.id.vez = v.id  WHERE p.resultado IS NULL AND v.dispositivo = :dispositivo")
    Optional<Jogo> findAtualByDispositivo(Dispositivo dispositivo);

}
