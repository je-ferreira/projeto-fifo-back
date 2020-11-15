package com.squad5.fifo.repository;

import com.squad5.fifo.model.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeReporsitory  extends JpaRepository<Node, Long> {

}
