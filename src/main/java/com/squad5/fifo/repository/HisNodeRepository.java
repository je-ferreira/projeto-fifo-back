package com.squad5.fifo.repository;

import com.squad5.fifo.model.HisNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HisNodeRepository extends JpaRepository<HisNode, Long> {

}
