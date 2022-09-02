package br.com.api.produtos.repositorio;

import br.com.api.produtos.modelo.ProdutoModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepositorio extends JpaRepository<ProdutoModelo, Long> {
}


