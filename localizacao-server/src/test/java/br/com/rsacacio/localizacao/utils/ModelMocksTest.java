package br.com.rsacacio.localizacao.utils;

import br.com.rsacacio.localizacao.model.Funcionario;
import br.com.rsacacio.localizacao.model.Loja;

public class ModelMocksTest {

    public static Loja criarLojaMock() {
        Loja loja = new Loja();
        loja.setId(1L);
        loja.setName("Loja 1");
        loja.setLatitude(-27.6066129);
        loja.setLongitude(-48.5803426);
        return loja;
    }

    public static Funcionario criarFuncionarioMock1() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);
        funcionario.setName("Funcionario 1");
        funcionario.setLatitude(-27.6019111);
        funcionario.setLongitude(-48.5957299);
        return funcionario;
    }

    public static Funcionario criarFuncionarioMock2() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(2L);
        funcionario.setName("Funcionario 2");
        funcionario.setLatitude(-27.6019111);
        funcionario.setLongitude(-48.5957299);
        return funcionario;
    }

}
