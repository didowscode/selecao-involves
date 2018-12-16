package br.com.rsacacio.localizacao.service;

import br.com.rsacacio.localizacao.model.Loja;
import br.com.rsacacio.localizacao.repository.LojaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LojaServiceImpl implements LojaService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private HaversineService haversineService;

    @Override
    public List<Loja> getLojasDistancia2Km(Double latitude, Double longitude) {
        logger.info("Buscando lojas na area de cobertura...");
        List<Loja> lojas = this.lojaRepository.findAll();
        lojas.forEach(loja -> {
            logger.info("======================================");
            logger.info("Calcular distância da loja " + loja.getName());
            loja.setDistancia(this.haversineService.getDistancia(latitude, longitude, loja.getLatitude(), loja.getLongitude()));
            logger.info("======================================");
        });
        return lojas.stream().filter(loja -> loja.getDistancia() <= 2.0).collect(Collectors.toList());
    }
}
