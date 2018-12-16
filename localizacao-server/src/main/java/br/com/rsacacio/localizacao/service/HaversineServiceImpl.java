package br.com.rsacacio.localizacao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HaversineServiceImpl implements HaversineService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final Integer RAIO_TERRA = 6371;

    @Override
    public double getDistancia(double latituteOrigem, double longitudeOrigem, double latitudeDestino, double longitudeDestino){
        logger.info("Calcular distância entre dois pontos " + latituteOrigem + "/" + longitudeOrigem + " e " + latitudeDestino + "/" + longitudeDestino);

        logger.info("Radianos da diferença da latitude destino e latitude origem");
        double radiansLatitudes  = Math.toRadians((latitudeDestino - latituteOrigem));
        logger.info("RADIANO(" + latitudeDestino + " - " + latituteOrigem + ") = " + radiansLatitudes);

        logger.info("Radianos da diferença da longitude destino e longitude origem");
        double radiansLongitudes = Math.toRadians((longitudeDestino - longitudeOrigem));
        logger.info("RADIANO(" + longitudeDestino + " - " + longitudeOrigem + ") = " + radiansLongitudes);

        logger.info("Radianos da latitude origem");
        double radiansLatitudeOrigem = Math.toRadians(latituteOrigem);
        logger.info("RADIANO(" + latituteOrigem + ") = " + radiansLatitudeOrigem);

        logger.info("Radianos da latitude origem");
        double radiansLatitudeDestino   = Math.toRadians(latitudeDestino);
        logger.info("RADIANO(" + latitudeDestino + ") = " + radiansLatitudeDestino);

        logger.info("Cosseno do radiano da latitude origem");
        double cossenoLatitudeOrigem = Math.cos(radiansLatitudeOrigem);
        logger.info("COSSENO(" + radiansLatitudeOrigem + ") = " + cossenoLatitudeOrigem);

        logger.info("Cosseno do radiano da latitude destino");
        double cossenoLatitudeDestino = Math.cos(radiansLatitudeDestino);
        logger.info("COSSENO(" + radiansLatitudeDestino + ") = " + cossenoLatitudeDestino);

        logger.info("SENO do radiano das latitudes");
        double senoLatitudes = Math.pow(Math.sin(radiansLatitudes / 2), 2);
        logger.info("SENO(" + radiansLatitudes + ") = " + senoLatitudes);

        logger.info("SENO do radiano das longitudes");
        double senoLongitudes = Math.pow(Math.sin(radiansLongitudes / 2), 2);
        logger.info("SENO(" + radiansLongitudes + ") = " + senoLongitudes);

        logger.info("Somatório interno");
        double somatorio = senoLatitudes + cossenoLatitudeOrigem * cossenoLatitudeDestino * senoLongitudes;
        logger.info(senoLatitudes + " (" + cossenoLatitudeOrigem + " * " + cossenoLatitudeDestino + " * " + senoLongitudes + ") = " + somatorio);

        logger.info("Calculando arcoseno");
        double arcoseno = 2 * Math.atan2(Math.sqrt(somatorio), Math.sqrt(1 - somatorio));
        logger.info("2 * ARCO_SENO(RAIZ_QUADRADA(" + somatorio + "), ARCO_SENO(RAIZ_QUADRADA(1 - " + somatorio + "))) = " + arcoseno);

        logger.info("Calcula da distancia em KM");
        double distancia = RAIO_TERRA * arcoseno;
        logger.info("RAIO_TERRA(" + RAIO_TERRA + ") = " + distancia);

        logger.info("Distância entre os pontos " + latituteOrigem + "/" + longitudeOrigem + " e " + latitudeDestino + "/" + longitudeDestino + " é: " + distancia);
        return distancia;
    }
}
