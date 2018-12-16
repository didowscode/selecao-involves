package br.com.rsacacio.localizacao.repository;

import br.com.rsacacio.localizacao.JpaConfiguration;
import br.com.rsacacio.localizacao.model.Funcionario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes= JpaConfiguration.class)
public class FuncionarioRepositoryTest {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Test
    public void whenFindAll() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setName("Funcionario 123");
        funcionario.setLatitude(-27.6019111);
        funcionario.setLongitude(-48.5957299);
        this.funcionarioRepository.save(funcionario);

        Funcionario funcionario2 = new Funcionario();
        funcionario2.setName("Funcionario 212");
        funcionario2.setLatitude(-27.6019111);
        funcionario2.setLongitude(-48.5957299);
        this.funcionarioRepository.save(funcionario2);

        //when
        List<Funcionario> funcionarios = this.funcionarioRepository.findAll();

        //then
        assertThat(funcionarios.size()).isEqualTo(7);
        assertThat(funcionarios.get(5)).isEqualTo(funcionario);
        assertThat(funcionarios.get(6)).isEqualTo(funcionario2);
    }

    @Test
    public void whenFindAllById() {
        //given
        Funcionario funcionario = new Funcionario();
        funcionario.setName("Funcionario 1");
        funcionario.setLatitude(-27.6019111);
        funcionario.setLongitude(-48.5957299);
        this.funcionarioRepository.save(funcionario);

        //when
        Funcionario testFuncionario = this.funcionarioRepository.findById(funcionario.getId()).get();

        //then
        assertThat(testFuncionario.getName()).isEqualTo(funcionario.getName());
    }

}
