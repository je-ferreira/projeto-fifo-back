package com.squad5.fifo.repository;

import com.squad5.fifo.model.CargoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoUsuarioRepository extends JpaRepository<CargoUsuario, Long> {

}
