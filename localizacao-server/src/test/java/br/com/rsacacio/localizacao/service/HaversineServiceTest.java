package br.com.rsacacio.localizacao.service;

import br.com.rsacacio.localizacao.JpaConfiguration;
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

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes= JpaConfiguration.class)
public class HaversineServiceTest {

    private static final Double latitude1 = -27.6019111;
    private static final Double longitude1 = -48.5957299;

    private static final Double latitude2 = -27.6066129;
    private static final Double longitude2 = -48.5803426;

    private static final Double distanciaParaMatch = 1.6038325476354314;

    @TestConfiguration
    static class HaversineServiceTestContextConfiguration {

        @Bean
        public HaversineService haversineService() {
            return new HaversineServiceImpl();
        }
    }

    @Autowired
    private HaversineService haversineService;

    @Test
    public void calculaDistanciaTest(){
        Double distancia = this.haversineService.getDistancia(HaversineServiceTest.latitude1, HaversineServiceTest.longitude1, HaversineServiceTest.latitude2, HaversineServiceTest.longitude2);
        assertThat(distancia).isEqualTo(distanciaParaMatch);
    }

    @Test
    public void calculaDistanciaErroTest(){
        Double latitudeDiferente = -27.6054435;
        Double distancia = this.haversineService.getDistancia(latitudeDiferente, HaversineServiceTest.longitude1, HaversineServiceTest.latitude2, HaversineServiceTest.longitude2);
        assertThat(distancia).isNotEqualTo(distanciaParaMatch);
    }
}
