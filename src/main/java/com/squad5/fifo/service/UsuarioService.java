package com.squad5.fifo.service;

import com.squad5.fifo.dto.UsuarioDTO;
import com.squad5.fifo.dto.UsuarioInsertDTO;
import com.squad5.fifo.dto.UsuarioUpdateDTO;
import com.squad5.fifo.model.CargoUsuario;
import com.squad5.fifo.model.Usuario;
import com.squad5.fifo.model.Vez;
import com.squad5.fifo.repository.UsuarioRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class UsuarioService {

    private static final String MSG_ID_NAO_ENCONTRADO = "Nenhum usuário com o id fornecido foi encontrado.";
    private static final String MSG_EMAIL_JA_CADASTRADO = "O email informado já foi usado por outro usuário.";

    private final UsuarioRepository usuarioRepository;

    private final ModelMapper modelMapper;

    @Setter(AccessLevel.PACKAGE)
    private VezService vezService;

    public UsuarioDTO findById(Long id) {
        return usuarioToUsuarioDTO(findModelById(id));
    }

    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(this::usuarioToUsuarioDTO)
                .collect(Collectors.toList());
    }
    
    public List<UsuarioDTO> findDisponiveis() {
    	return usuarioRepository.findByVezNullAndAtivo(true).stream()
                .map(this::usuarioToUsuarioDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO insert(UsuarioInsertDTO usuarioInsertDTO) {
        if(usuarioRepository.findByEmail(usuarioInsertDTO.getEmail()).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_EMAIL_JA_CADASTRADO);
        Usuario usuario = usuarioDTOToUsuario(usuarioInsertDTO);
        usuario.setCargoUsuario(CargoUsuario.USER);
        return usuarioToUsuarioDTO(usuarioRepository.save(usuario));
    }

    public UsuarioDTO update(UsuarioUpdateDTO usuarioUpdateDTO) {
        Usuario usuario = findModelById(usuarioUpdateDTO.getId());
        if(usuarioUpdateDTO.getEmail() != null &&
                !usuarioUpdateDTO.getEmail().equals(usuario.getEmail()) &&
                usuarioRepository.findByEmail(usuarioUpdateDTO.getEmail()).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_EMAIL_JA_CADASTRADO);

        modelMapper.map(usuarioUpdateDTO, usuario);
        usuario.setVez(mergeIdToNull(usuarioUpdateDTO.getVez(), 0L, usuario.getVez(), vezService::findModelById));

        return usuarioToUsuarioDTO(usuarioRepository.save(usuario));
    }

    public void deleteById(Long id) {
        findModelById(id);
        usuarioRepository.deleteById(id);
    }

    Usuario findModelById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_NAO_ENCONTRADO)
        );
    }

    UsuarioDTO usuarioToUsuarioDTO(Usuario usuario){
        UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        usuarioDTO.setVez(usuario.getVez() == null ? null : usuario.getVez().getId());
        return usuarioDTO;
    }

    Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        if(usuarioDTO.getVez() != null)
            usuario.setVez(vezService.findModelById(usuarioDTO.getVez()));

        return usuario;
    }

    List<Usuario> findByVez(Vez vez) {
        return usuarioRepository.findByVez(vez);
    }

    private <T, U> T mergeIdToNull(U id, U nullCase, T atual, Function<U, T> finder){
        if(id == null) return atual;
        if(id.equals(nullCase)) return null;
        return finder.apply(id);
    }

}
