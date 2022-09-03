package br.com.api.produtos.servico;

import br.com.api.produtos.dto.InserirProdutoDTO;
import br.com.api.produtos.dto.ProdutoDTO;
import br.com.api.produtos.modelo.ProdutoModelo;
import br.com.api.produtos.modelo.RespostaModelo;
import br.com.api.produtos.repositorio.ProdutoRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class ProdutoServico {
    @Autowired
    private ProdutoRepositorio repositorio;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RespostaModelo respostaModelo;

    public Page<ProdutoDTO> buscarTodos(Pageable pageable) {
        Page<ProdutoModelo> page = repositorio.findAll(pageable);

        return page.map(x -> mapper.map(x, ProdutoDTO.class));
    }

    public ProdutoDTO inserirProduto(InserirProdutoDTO obj) {
        ProdutoModelo modelo = new ProdutoModelo();
        modelo = mapper.map(obj, ProdutoModelo.class);
        modelo = repositorio.save(modelo);

        return mapper.map(modelo, ProdutoDTO.class);
    }


    public Iterable<ProdutoModelo> listar() {
        return repositorio.findAll();
    }

    //método para cadastrar ou alterar produto
    public ResponseEntity<?> cadastrarAlterar(ProdutoModelo modelo, String acao) {
        //evita null pointer exception
        if (modelo.getNome() == null) {
            modelo.setNome("");
        }

        if (modelo.getMarca() == null) {
            modelo.setMarca("");
        }

        if (modelo.getNome().equals("")) {
            respostaModelo.setMensagem("O nome do produto é obrigatório!");
            return new ResponseEntity<RespostaModelo>(respostaModelo, HttpStatus.BAD_REQUEST);
        } else if (modelo.getMarca().equals("")) {
            respostaModelo.setMensagem("O nome da marca do produto é obrigatório!");
            return new ResponseEntity<RespostaModelo>(respostaModelo, HttpStatus.BAD_REQUEST);
        } else {
            modelo = repositorio.save(modelo);
            if (acao.equals("cadastrar")) {
                return new ResponseEntity<ProdutoModelo>(modelo, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<ProdutoModelo>(modelo, HttpStatus.OK);
            }
        }
    }

    public ProdutoDTO atualizarProduto(Long codigo, InserirProdutoDTO obj) {
        ProdutoModelo modelo = repositorio.getReferenceById(codigo);
        modelo.setNome(obj.getNome());
        modelo.setMarca(obj.getMarca());
        modelo = repositorio.save(modelo);
        
        return mapper.map(modelo, ProdutoDTO.class);
    }
}



