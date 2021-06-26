package br.springboot.ExemploProdutoAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


// classe de acesso bo backend ao mongodb
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    //Create operation
    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    //Retrieve operation
    public List<Produto> getAll() {
        return produtoRepository.findAll();
    }
    public Produto getByNome(String nome) {
        return produtoRepository.findByNome(nome);
    }

    //Update operation
    public Produto update(Produto produto) {
        return produtoRepository.save(produto);
    }

    //Delete operation
    public void delete(Produto produto) {
        produtoRepository.delete(produto);
    }
}
