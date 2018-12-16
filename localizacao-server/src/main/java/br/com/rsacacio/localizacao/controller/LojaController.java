package br.com.rsacacio.localizacao.controller;

import br.com.rsacacio.localizacao.model.Loja;
import br.com.rsacacio.localizacao.service.LojaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("localizacao/loja")
public class LojaController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LojaService lojaService;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Loja> findAll(
            @RequestParam(required = false, name = "latitude") Double latitude,
            @RequestParam(required = false, name = "longitude") Double longitude
    ) {
        return this.lojaService.getLojasDistancia2Km(latitude, longitude);
    }
}

//    docker run -it --link docker-postgres -p 8080:8080 rsacacio/localizacao-server-app
