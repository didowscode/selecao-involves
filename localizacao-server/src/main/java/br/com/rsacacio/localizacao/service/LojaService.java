package br.com.rsacacio.localizacao.service;

import br.com.rsacacio.localizacao.model.Loja;

import java.util.List;

public interface LojaService {

    List<Loja> getLojasDistancia2Km(Double latitude, Double longitude);

}
