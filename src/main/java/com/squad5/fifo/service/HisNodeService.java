package com.squad5.fifo.service;

import com.squad5.fifo.model.HisMetodo;
import com.squad5.fifo.model.HisNode;
import com.squad5.fifo.model.Node;
import com.squad5.fifo.repository.HisNodeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service @RequiredArgsConstructor
public class HisNodeService {

    private static final String MSG_ID_NAO_ENCONTRADO = "Nenhum node em histórico com o id fornecido foi encontrado.";

    private final HisNodeRepository hisNodeRepository;

    private final UsuarioService usuarioService;

    private final ModelMapper modelMapper;

    HisNode findModelById(Long id) {
        return hisNodeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_NAO_ENCONTRADO)
        );
    }

    List<HisNode> findAllModels() {
        return hisNodeRepository.findAll();
    }

    HisNode insertNewModel(Node node, HisMetodo hisMetodo) {
        return hisNodeRepository.save(HisNode.builder()
                .id(node.getId())
                .entrada(node.getEntrada())
                .hisMetodo(hisMetodo)
                .usuarioList(usuarioService.findAllByNodeId(node.getId()))
                .build());
    }

    HisNode updateModel(Long id, HisNode superHisNode) {
        HisNode hisNode = findModelById(id);
        hisNode.setSuperHisNode(superHisNode);
        return hisNodeRepository.save(hisNode);
    }

}
