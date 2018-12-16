package br.com.rsacacio.localizacao.repository;

import br.com.rsacacio.localizacao.model.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long> {
}
