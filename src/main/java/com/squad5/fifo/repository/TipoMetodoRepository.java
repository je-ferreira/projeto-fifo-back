package com.squad5.fifo.repository;

import com.squad5.fifo.model.TipoMetodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoMetodoRepository extends JpaRepository<TipoMetodo, Long> {

}
