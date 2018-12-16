package br.com.rsacacio.localizacao.service;

public interface HaversineService {

    double getDistancia(double latituteOrigem, double longitudeOrigem, double latitudeDestino, double longitudeDestino);
}
