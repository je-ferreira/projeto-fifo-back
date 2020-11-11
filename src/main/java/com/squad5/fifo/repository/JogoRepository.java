package com.squad5.fifo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.squad5.fifo.model.Jogo;

public interface JogoRepository extends JpaRepository<Jogo, Long> {

	Optional<Jogo> findByNome(String nome);
}
