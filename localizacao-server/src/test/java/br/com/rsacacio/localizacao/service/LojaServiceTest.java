package br.com.rsacacio.localizacao.service;

import br.com.rsacacio.localizacao.JpaConfiguration;
import br.com.rsacacio.localizacao.model.Loja;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = JpaConfiguration.class)
public class LojaServiceTest {

    private static final Double latitude = -27.6019111;
    private static final Double longitude = -48.5957299;

    private static final Double latitude2 = -28.6019111;
    private static final Double longitude2 = -49.5957299;

    private static final Double distanciaMaxima = 2.0;

    @TestConfiguration
    static class LojaServiceTestContextConfiguration {

        @Bean
        public LojaService lojaService() {
            return new LojaServiceImpl();
        }
    }

    @Autowired
    private LojaService lojaService;

    @Test
    public void getLojasDistancia2Km() {
        List<Loja> lojas = this.lojaService.getLojasDistancia2Km(latitude, longitude);

        assertThat(lojas).isNotNull();
        assertThat(lojas).isNotEmpty();
        lojas.forEach(loja ->
                assertThat(loja.getDistancia()).isLessThanOrEqualTo(distanciaMaxima)
        );
    }

    @Test
    public void getLojasDistancia2KmSemResultados() {
        List<Loja> lojas = this.lojaService.getLojasDistancia2Km(latitude2, longitude2);

        assertThat(lojas).isNotNull();
        assertThat(lojas).isEmpty();
    }


}
