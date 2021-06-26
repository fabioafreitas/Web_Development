package br.springboot.ExemploProdutoAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<?> listAll() {
        List<Produto> produtoList = produtoService.getAll();
        if(produtoList.isEmpty()) {
            return new ResponseEntity<>("Não há produtos cadastrados",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(produtoList, HttpStatus.OK);
    }

    @GetMapping(path = "/{nome}")
    public ResponseEntity<?> getProdutoByNome(@PathVariable("nome") String nome) {
        Produto produto = produtoService.getByNome(nome);
        if(produto == null) {
            return new ResponseEntity<>("Produto não encontrado",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Produto produto) {
        Produto p = produtoService.getByNome(produto.getNome());
        if(p == null) {
            return new ResponseEntity<>(produtoService.save(produto), HttpStatus.OK);
        }
        return new ResponseEntity<>("Produto já existe!",HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Produto produto) {
        Produto p = produtoService.getByNome(produto.getNome());
        if(p == null) {
            return new ResponseEntity<>("Produto não existe", HttpStatus.NOT_FOUND);
        }
        produto.setId(p.getId());
        produtoService.save(produto);
        return new ResponseEntity<>("Alterado com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Produto produto) {
        Produto p = produtoService.getByNome(produto.getNome());
        if (p == null) {
            return new ResponseEntity<>("Produto não existe", HttpStatus.NOT_FOUND);
        }
        produtoService.delete(p);
        return new ResponseEntity<>("Produto deletado", HttpStatus.OK);
    }
}
