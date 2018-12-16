package br.com.rsacacio.localizacao.service;

import br.com.rsacacio.localizacao.model.Funcionario;
import br.com.rsacacio.localizacao.model.Loja;
import com.opencsv.bean.CsvToBean;

import java.io.Reader;
import java.util.List;

public interface PopularDatabaseService {

    Reader readFile(String file) throws Exception;

    List<Funcionario> popularFuncionario() throws Exception;

    CsvToBean<Funcionario> loadFuncionarios(Reader reader);

    List<Loja> popularLoja() throws Exception;

    CsvToBean<Loja> loadLojas(Reader reader);
}
