package com.squad5.fifo.service;

import com.squad5.fifo.dto.UsuarioDTO;
import com.squad5.fifo.dto.UsuarioInsertDTO;
import com.squad5.fifo.dto.UsuarioUpdateDTO;
import com.squad5.fifo.model.Usuario;
import com.squad5.fifo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class UsuarioService {

    private static final String MSG_ID_NAO_ENCONTRADO = "Nenhum usuário com o id fornecido foi encontrado.";
    private static final String MSG_EMAIL_JA_CADASTRADO = "O email informado já foi usado por outro usuário.";

    private final UsuarioRepository usuarioRepository;

    private final ModelMapper modelMapper;

    public UsuarioDTO findById(Long id) {
        return usuarioToUsuarioDTO(validateId(id));
    }

    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(this::usuarioToUsuarioDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO insert(UsuarioInsertDTO usuarioInsertDTO) {
        if(usuarioRepository.findByEmail(usuarioInsertDTO.getEmail()).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_EMAIL_JA_CADASTRADO);

        Usuario usuario = usuarioDTOToUsuario(usuarioInsertDTO);
        return usuarioToUsuarioDTO(usuarioRepository.save(usuario));
    }

    public UsuarioDTO update(UsuarioUpdateDTO usuarioUpdateDTO) {
        Usuario usuario = validateId(usuarioUpdateDTO.getId());
        if(usuarioUpdateDTO.getEmail() != null &&
                !usuarioUpdateDTO.getEmail().equals(usuario.getEmail()) &&
                usuarioRepository.findByEmail(usuarioUpdateDTO.getEmail()).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_EMAIL_JA_CADASTRADO);

        modelMapper.map(usuarioUpdateDTO, usuario);
        return usuarioToUsuarioDTO(usuarioRepository.save(usuario));
    }

    public void deleteById(long id) {
        validateId(id);
        usuarioRepository.deleteById(id);
    }

    private Usuario validateId(long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_NAO_ENCONTRADO)
        );
    }

    private UsuarioDTO usuarioToUsuarioDTO(Usuario usuario){
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    private Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO){
        return modelMapper.map(usuarioDTO, Usuario.class);
    }

}
