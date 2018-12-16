package br.com.rsacacio.localizacao.controller;

import br.com.rsacacio.localizacao.model.Funcionario;
import br.com.rsacacio.localizacao.repository.FuncionarioRepository;
import exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("localizacao/funcionario")
public class FuncionarioController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Funcionario> findAll() {
        logger.info("Buscando dados de todos os funcionários");
        return this.funcionarioRepository.findAll();
    }

    @RequestMapping(value = "/{funcionarioId}", method = RequestMethod.GET)
    public Funcionario get(@PathVariable("funcionarioId")  Long funcionarioId) {
        logger.info("Buscando dados do funcionário " + funcionarioId);
        return this.funcionarioRepository.findById(funcionarioId).get();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Funcionario save(
            @Valid @RequestBody Funcionario funcionario
    ) {
        logger.info("Salvando dados do funcionário " + funcionario);
        return this.funcionarioRepository.save(funcionario);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{funcionarioId}")
    public ResponseEntity<?> delete(@PathVariable Long funcionarioId) {
        logger.info("Removendo dados do funcionário " + funcionarioId);
        return funcionarioRepository.findById(funcionarioId)
                .map(question -> {
                    funcionarioRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com o id " + funcionarioId));
    }


}
