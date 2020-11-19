package com.squad5.fifo.controller;

import com.squad5.fifo.dto.HomePartidaAtualDTO;
import com.squad5.fifo.dto.JogoDTO;
import com.squad5.fifo.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/partidas")
    public List<HomePartidaAtualDTO> partidasAtuais() {
        return homeService.findPartidasAtuais();
    }

    @GetMapping("/jogos")
    public List<JogoDTO> jogosAtivos() {
        return homeService.findJogosAtivos();
    }

}
