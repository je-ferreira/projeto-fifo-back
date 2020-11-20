package com.squad5.fifo.service;

import com.squad5.fifo.model.Metodo;
import com.squad5.fifo.model.Node;
import com.squad5.fifo.repository.NodeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service @RequiredArgsConstructor
public class NodeService {

    private static final String MSG_ID_NAO_ENCONTRADO = "Nenhum node com o id fornecido foi encontrado.";

    private final NodeRepository nodeReporsitory;

    private final ModelMapper modelMapper;

    Node findModelById(Long id) {
        return nodeReporsitory.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_NAO_ENCONTRADO)
        );
    }

    List<Node> findAllModels() {
        return nodeReporsitory.findAll();
    }

    Node insertNewModel(Metodo metodo) {
        return nodeReporsitory.save(Node.builder()
                .entrada(new Date())
                .metodo(metodo)
                .build());
    }

    Node updateModel(Node node) {
        Node nodeAtual = findModelById(node.getId());
        node.setMetodo(nodeAtual.getMetodo());
        modelMapper.map(node, nodeAtual);
        return nodeReporsitory.save(nodeAtual);
    }

    void deleteById(Long id) {
        findModelById(id);
        nodeReporsitory.deleteById(id);
    }

}
