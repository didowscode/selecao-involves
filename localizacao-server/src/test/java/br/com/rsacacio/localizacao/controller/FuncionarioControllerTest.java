package br.com.rsacacio.localizacao.controller;

import br.com.rsacacio.localizacao.model.Funcionario;
import br.com.rsacacio.localizacao.utils.ModelMocksTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FuncionarioController.class)
public class FuncionarioControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FuncionarioController funcionarioController;

    @Test
    public void findAll() throws Exception{
        Funcionario funcionario = ModelMocksTest.criarFuncionarioMock1();

        List<Funcionario> funcionarios = singletonList(funcionario);
        given(this.funcionarioController.findAll()).willReturn(funcionarios);

        this.mvc.perform(get(new URI("/localizacao/funcionario/"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(funcionario.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(funcionario.getName())))
                .andExpect(jsonPath("$[0].latitude", is(funcionario.getLatitude())))
                .andExpect(jsonPath("$[0].longitude", is(funcionario.getLongitude())));

    }

    @Test
    public void getById() throws Exception{
        Funcionario funcionario = ModelMocksTest.criarFuncionarioMock1();
        given(this.funcionarioController.get(funcionario.getId())).willReturn(funcionario);

        mvc.perform(get(new URI("/localizacao/funcionario/" + String.valueOf(funcionario.getId())))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(funcionario.getId().intValue())))
                .andExpect(jsonPath("name", is(funcionario.getName())))
                .andExpect(jsonPath("latitude", is(funcionario.getLatitude())))
                .andExpect(jsonPath("longitude", is(funcionario.getLongitude())));
    }

    @Test
    public void save() throws Exception{
        Funcionario funcionario = ModelMocksTest.criarFuncionarioMock1();
        given(this.funcionarioController.save(funcionario)).willReturn(funcionario);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson= ow.writeValueAsString(funcionario );

        mvc.perform(post(new URI("/localizacao/funcionario/save"))
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void saveComErro() throws Exception{
        mvc.perform(post(new URI("/localizacao/funcionario/save"))
                .content("")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

}
