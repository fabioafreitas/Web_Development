package br.springboot.ExemploPessoaAPI;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends MongoRepository<Pessoa, String> {
    public Pessoa findByCpf(String cpf);
    public List<Pessoa> findByNome(String nome);
    public List<Pessoa> findByIdade(int idade);
}
