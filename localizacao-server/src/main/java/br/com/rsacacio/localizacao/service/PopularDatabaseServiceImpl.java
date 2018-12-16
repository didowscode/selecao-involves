package br.com.rsacacio.localizacao.service;

import br.com.rsacacio.localizacao.model.Funcionario;
import br.com.rsacacio.localizacao.model.Loja;
import br.com.rsacacio.localizacao.repository.FuncionarioRepository;
import br.com.rsacacio.localizacao.repository.LojaRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class PopularDatabaseServiceImpl implements PopularDatabaseService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String FUNCIONARIO_FILE = "funcionarios.csv";

    private static final String LOJAS_FILE = "lojas.csv";

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private LojaRepository lojaRepository;

    @Override
    public Reader readFile(String file) throws Exception {
        Resource resource = new ClassPathResource(file);
        return new BufferedReader(new InputStreamReader(resource.getInputStream()));
//        reader.lines().forEach(System.out::println);
//        return Files.newBufferedReader(Paths.get(Thread.currentThread().getContextClassLoader().getResource(file).toURI()));
    }

    @Override
    public List<Funcionario> popularFuncionario() throws Exception {
        logger.info("Iniciando o preenchimento da tabela de funcionários...");
        try (
                Reader reader = this.readFile(FUNCIONARIO_FILE)
        ) {
            CsvToBean<Funcionario> csvToBean = this.loadFuncionarios(reader);
            logger.info("Lista de funcionários lidas do arquivo " + FUNCIONARIO_FILE + "...");
            List<Funcionario> funcionarios = this.funcionarioRepository.saveAll(csvToBean);
            logger.info("Preenchimento da tabela de funcionários concluído...");
            return funcionarios;
        }
    }

    @Override
    public CsvToBean<Funcionario> loadFuncionarios(Reader reader){
        logger.info("Ler lista de funcionários do "+ FUNCIONARIO_FILE + "...");
        return new CsvToBeanBuilder<Funcionario>(reader)
                .withType(Funcionario.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
    }

    @Override
    public List<Loja> popularLoja() throws Exception {
        logger.info("Iniciando o preenchimento da tabela de lojas...");
        try (
                Reader reader = this.readFile(LOJAS_FILE)
        ) {
            CsvToBean<Loja> csvToBean = this.loadLojas(reader);
            logger.info("Lista de lojas lidas do arquivo " + LOJAS_FILE + "...");
            List<Loja> lojas = this.lojaRepository.saveAll(csvToBean);
            logger.info("Preenchimento da tabela de lojas concluído...");
            return lojas;
        }
    }

    @Override
    public CsvToBean<Loja> loadLojas(Reader reader){
        logger.info("Ler lista de lojas do "+ LOJAS_FILE + "...");
        return new CsvToBeanBuilder<Loja>(reader)
                .withType(Loja.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
    }


}
