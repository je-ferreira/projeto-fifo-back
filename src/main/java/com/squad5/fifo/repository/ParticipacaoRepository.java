package com.squad5.fifo.repository;

import com.squad5.fifo.model.Participacao;
import com.squad5.fifo.model.ParticipacaoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipacaoRepository extends JpaRepository<Participacao, ParticipacaoId> {

    List<Participacao> findByResultadoNull();

}
