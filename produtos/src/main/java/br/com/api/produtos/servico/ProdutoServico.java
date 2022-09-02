package br.com.api.produtos.servico;

import br.com.api.produtos.dto.InserirProdutoDTO;
import br.com.api.produtos.dto.ProdutoDTO;
import br.com.api.produtos.modelo.ProdutoModelo;
import br.com.api.produtos.repositorio.ProdutoRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ProdutoServico {

    private final ProdutoRepositorio repositorio;
    private final ModelMapper mapper;

    public ProdutoServico(ProdutoRepositorio repositorio, ModelMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    public Page<ProdutoDTO> buscarTodos(Pageable pageable) {
        Page<ProdutoModelo> page = repositorio.findAll(pageable);

        return page.map(x -> mapper.map(x, ProdutoDTO.class));
    }


    public Iterable<ProdutoModelo> listar() {
        return repositorio.findAll();
    }

    public ProdutoDTO inserirProduto(InserirProdutoDTO obj) {
        ProdutoModelo modelo = new ProdutoModelo();
        modelo = mapper.map(obj, ProdutoModelo.class);
        modelo = repositorio.save(modelo);

        return mapper.map(modelo, ProdutoDTO.class);
    }
}



