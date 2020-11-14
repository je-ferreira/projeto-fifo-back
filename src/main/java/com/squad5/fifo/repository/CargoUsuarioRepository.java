package com.squad5.fifo.repository;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.squad5.fifo.model.CargoUsuario;
 
@Repository
public interface CargoUsuarioRepository extends JpaRepository<CargoUsuario, Long> {

}