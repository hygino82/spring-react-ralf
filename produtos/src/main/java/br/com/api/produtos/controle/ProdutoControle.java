package br.com.api.produtos.controle;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.produtos.dto.InserirProdutoDTO;
import br.com.api.produtos.dto.ProdutoDTO;
import br.com.api.produtos.modelo.ProdutoModelo;
import br.com.api.produtos.servico.ProdutoServico;


@RestController
@RequestMapping("/api/v1/produto")
@CrossOrigin(origins = "*")
public class ProdutoControle {

    private final ProdutoServico servico;

    public ProdutoControle(ProdutoServico servico) {
        this.servico = servico;
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> buscarTodos(Pageable pageable) {
        Page<ProdutoDTO> pageDto = servico.buscarTodos(pageable);

        return ResponseEntity.ok().body(pageDto);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPorCodigo(@PathVariable Long codigo) {
        return servico.buscarPorCodigo(codigo);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> inserirProduto(@Valid @RequestBody InserirProdutoDTO obj) {
        ProdutoDTO dto = servico.inserirProduto(obj);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{codigo}")
    public void removerProduto(@PathVariable Long codigo) {
        servico.removerProduto(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Long codigo, @RequestBody InserirProdutoDTO obj) {
        ProdutoDTO dto = servico.atualizarProduto(codigo, obj);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/listar")
    public Iterable<ProdutoModelo> listar() {
        return servico.listar();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody ProdutoModelo modelo) {
        return servico.cadastrarAlterar(modelo, "cadastrar");
    }

    @PutMapping("/alterar")
    public ResponseEntity<?> alterar(@RequestBody ProdutoModelo modelo) {
        return servico.cadastrarAlterar(modelo, "alterar");
    }

    @DeleteMapping("/remover/{codigo}")
    public ResponseEntity<?> remover(@PathVariable Long codigo) {
        return servico.remover(codigo);
    }
}
