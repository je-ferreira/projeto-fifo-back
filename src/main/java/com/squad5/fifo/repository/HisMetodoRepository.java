package com.squad5.fifo.repository;

import com.squad5.fifo.model.HisMetodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HisMetodoRepository extends JpaRepository<HisMetodo, Long> {

}
