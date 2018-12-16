package br.com.rsacacio.localizacao.controller;

import br.com.rsacacio.localizacao.model.Funcionario;
import br.com.rsacacio.localizacao.model.Loja;
import br.com.rsacacio.localizacao.utils.ModelMocksTest;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LojaController.class)
public class LojaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LojaController lojaController;

    @Test
    public void findAll() throws Exception{
        Loja loja = ModelMocksTest.criarLojaMock();
        Funcionario funcionario = ModelMocksTest.criarFuncionarioMock1();

        List<Loja> lojas = singletonList(loja);
        given(this.lojaController.findAll(funcionario.getLatitude(), funcionario.getLongitude())).willReturn(lojas);

        this.mvc.perform(get(new URI("/localizacao/loja/"))
                .param("latitude", String.valueOf(funcionario.getLatitude()))
                .param("longitude", String.valueOf(funcionario.getLongitude()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(loja.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(loja.getName())))
                .andExpect(jsonPath("$[0].latitude", is(loja.getLatitude())))
                .andExpect(jsonPath("$[0].longitude", is(loja.getLongitude())));
    }

}
