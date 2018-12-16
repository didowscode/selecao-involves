package br.com.rsacacio.localizacao.service;

import br.com.rsacacio.localizacao.JpaConfiguration;
import br.com.rsacacio.localizacao.model.Funcionario;
import br.com.rsacacio.localizacao.model.Loja;
import br.com.rsacacio.localizacao.repository.FuncionarioRepository;
import br.com.rsacacio.localizacao.repository.LojaRepository;
import com.opencsv.bean.CsvToBean;
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

import java.io.Reader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes= JpaConfiguration.class)
public class PopularDatabaseTest {

    @TestConfiguration
    static class PopularDatabaseTestContextConfiguration {

        @Bean
        public PopularDatabaseService popularDatabaseService() {
            return new PopularDatabaseServiceImpl();
        }
    }

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private PopularDatabaseService popularDatabaseService;

    @Test
    public void readerTest() {
        try {
            Reader reader = this.popularDatabaseService.readFile("funcionarios.csv");
            assertThat(reader).isNotNull();
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void readerErroTest() {
        try {
            Reader reader = this.popularDatabaseService.readFile("funcionarios_NOME_ERRADO.csv");
            assertThat(reader).isNull();
        } catch (Exception e) {
            assert true;
        }
    }

    @Test
    public void loadFuncionariosTest() throws Exception {
        Reader reader = this.popularDatabaseService.readFile("funcionarios.csv");
        CsvToBean<Funcionario> funcionarioCsvToBean = this.popularDatabaseService.loadFuncionarios(reader);

        assertThat(funcionarioCsvToBean).isNotNull();
        assertThat(funcionarioCsvToBean).isNotEmpty();

        List<Funcionario> funcionarios = funcionarioCsvToBean.parse();
        assertThat(funcionarios).isNotEmpty();
    }

    @Test
    public void loadPopulaFuncionariosTest() throws Exception {
        List<Funcionario> funcionarios = this.popularDatabaseService.popularFuncionario();
        assertThat(funcionarios).isNotEmpty();
        assertThat(this.funcionarioRepository.findAll()).isNotEmpty();
    }

    @Test
    public void loadFuncionariosErroTest() throws Exception {
        Reader reader = this.popularDatabaseService.readFile("funcionarios_empty.csv");
        CsvToBean<Funcionario> funcionarioCsvToBean = this.popularDatabaseService.loadFuncionarios(reader);

        assertThat(funcionarioCsvToBean).isNotNull();
        assertThat(funcionarioCsvToBean).isEmpty();

        List<Funcionario> funcionarios = funcionarioCsvToBean.parse();
        assertThat(funcionarios).isEmpty();
    }

    @Test
    public void loadLojaTest() throws Exception {
        Reader reader = this.popularDatabaseService.readFile("lojas.csv");
        CsvToBean<Loja> lojaCsvToBean = this.popularDatabaseService.loadLojas(reader);

        assertThat(lojaCsvToBean).isNotNull();
        assertThat(lojaCsvToBean).isNotEmpty();

        List<Loja> lojas = lojaCsvToBean.parse();
        assertThat(lojas).isNotEmpty();
    }

    @Test
    public void loadPopulaLojaTest() throws Exception {
        List<Loja> lojas = this.popularDatabaseService.popularLoja();
        assertThat(lojas).isNotEmpty();
        assertThat(this.lojaRepository.findAll()).isNotEmpty();
    }

    @Test
    public void loadLojaErroTest() throws Exception {
        Reader reader = this.popularDatabaseService.readFile("lojas_empty.csv");
        CsvToBean<Loja> lojaCsvToBean = this.popularDatabaseService.loadLojas(reader);

        assertThat(lojaCsvToBean).isNotNull();
        assertThat(lojaCsvToBean).isEmpty();

        List<Loja> lojas = lojaCsvToBean.parse();
        assertThat(lojas).isEmpty();
    }

}
