package br.com.rsacacio.localizacao;

import br.com.rsacacio.localizacao.service.PopularDatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PopularDatabaseService popularDatabaseService;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        try {
            logger.info("Iniciando o preenchimento das tabelas com base nos arquivos...");
            this.popularDatabaseService.popularFuncionario();
            this.popularDatabaseService.popularLoja();
            logger.info("Preenchimento das tabelas concluído...");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

}