package com.squad5.fifo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

 
import com.squad5.fifo.model.Node;

public interface NodeReporsitory  extends JpaRepository<Node, Long> {

}
