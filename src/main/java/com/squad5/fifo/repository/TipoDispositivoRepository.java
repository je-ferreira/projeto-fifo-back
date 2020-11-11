package com.squad5.fifo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.squad5.fifo.model.TipoDispositivo;

public interface TipoDispositivoRepository extends JpaRepository<TipoDispositivo, Long> {

	Optional<TipoDispositivo> findByNome(String nome);
}
