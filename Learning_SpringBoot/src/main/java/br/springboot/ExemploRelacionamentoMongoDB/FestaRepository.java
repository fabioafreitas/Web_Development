package br.springboot.ExemploRelacionamentoMongoDB;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FestaRepository extends MongoRepository<Festa, String> {
    public Festa findByMatricula(String matricula);
}
