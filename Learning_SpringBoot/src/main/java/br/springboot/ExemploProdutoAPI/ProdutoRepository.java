package br.springboot.ExemploProdutoAPI;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProdutoRepository extends MongoRepository<Produto, String> {
    public Produto findByNome(String nome);
}
