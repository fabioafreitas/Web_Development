package br.springboot.ExemploHeranca;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArvoreRepository extends MongoRepository<Arvore, String> {
    public Arvore findByIdade(int idade);
}
