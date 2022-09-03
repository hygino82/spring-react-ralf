package br.com.api.produtos.controle;

import br.com.api.produtos.dto.InserirProdutoDTO;
import br.com.api.produtos.dto.ProdutoDTO;
import br.com.api.produtos.modelo.ProdutoModelo;
import br.com.api.produtos.servico.ProdutoServico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/produto")
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

    @PostMapping
    public ResponseEntity<ProdutoDTO> inserirProduto(@RequestBody InserirProdutoDTO obj) {
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
    public ResponseEntity<?> remover(@PathVariable Long codigo){
        return servico.remover(codigo);
    }
}
