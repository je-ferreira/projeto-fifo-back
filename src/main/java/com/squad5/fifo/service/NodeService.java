package com.squad5.fifo.service;

import com.squad5.fifo.model.Node;
import com.squad5.fifo.repository.NodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service @RequiredArgsConstructor
public class NodeService {

    private static final String MSG_ID_NAO_ENCONTRADO = "Nenhum node com o id fornecido foi encontrado.";

    private final NodeRepository nodeReporsitory;

    Node findModelById(Long id) {
        return nodeReporsitory.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_NAO_ENCONTRADO)
        );
    }

}
