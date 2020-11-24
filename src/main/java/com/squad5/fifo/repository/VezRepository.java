package com.squad5.fifo.repository;

import com.squad5.fifo.model.Vez;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VezRepository extends JpaRepository<Vez, Long> {

}
