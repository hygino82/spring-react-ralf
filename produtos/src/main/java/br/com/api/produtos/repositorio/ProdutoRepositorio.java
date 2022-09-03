package br.com.api.produtos.repositorio;

import br.com.api.produtos.modelo.ProdutoModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepositorio extends JpaRepository<ProdutoModelo, Long> {
    Optional<ProdutoModelo> findByCodigo(Long codigo);
}


