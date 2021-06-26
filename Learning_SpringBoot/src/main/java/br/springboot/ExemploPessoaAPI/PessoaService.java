package br.springboot.ExemploPessoaAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;


    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }



    public List<Pessoa> getAll() {
        return pessoaRepository.findAll();
    }
    public Pessoa getByCpf(String cpf) {
        return pessoaRepository.findByCpf(cpf);
    }
    public List<Pessoa> getByNome(String nome) {
        return pessoaRepository.findByNome(nome);
    }
    public List<Pessoa> getByIdade(int idade) {
        return pessoaRepository.findByIdade(idade);
    }



    public Pessoa update(Pessoa pessoa) {
       Pessoa pAux = pessoaRepository.findByCpf(pessoa.getCpf());
       pAux.setIdade(pessoa.getIdade());
       pAux.setNome(pessoa.getNome());
       return pessoaRepository.save(pAux);
    }


    public void deleteAll() {
        pessoaRepository.deleteAll();
    }
    public boolean delete(Pessoa pessoa) {
        Pessoa pAux = pessoaRepository.findByCpf(pessoa.getCpf());
        if(pAux != null) {
            pessoaRepository.delete(pAux);
            return true;
        }
        return false;
    }

}
