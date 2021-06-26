package br.springboot.ExemploRelacionamentoMongoDB;

import br.springboot.ExemploPessoaAPI.Pessoa;
import br.springboot.ExemploPessoaAPI.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FestaService {

    @Autowired
    private FestaRepository festaRepository;

    @Autowired
    private PessoaService pessoaService;

    public List<Festa> getAll() { //GET recuperar todas as festas
        return festaRepository.findAll();
    }

    public Festa getByMatricula(String matricula) { //GET recuperar festa por matricula
        return festaRepository.findByMatricula(matricula);
    }

    public Festa save(Festa festa) { //POST cadastrar nova festa
        Festa f = festaRepository.findByMatricula(festa.getMatricula());
        if(f == null) {
            return festaRepository.save(festa);
        }
        return null;
    }

    public Festa desconvidar(Festa festa, Pessoa pessoa) { //PUT desconvidar alguem de alguma festa
        Pessoa p = pessoaService.getByCpf(pessoa.getCpf());
        if(p == null) return null;
        Festa f = festaRepository.findByMatricula(festa.getMatricula());
        if(f == null) return null;
        if(!f.getConvidados().contains(p)) return null;
        f.getConvidados().remove(p);
        return festaRepository.save(f);

    }

    // convida uma pessoa
    public Festa convidar(Festa festa, Pessoa pessoa) { //PUT convidar alguem novo
        Pessoa p = pessoaService.getByCpf(pessoa.getCpf());
        if(p == null) return null;
        Festa f = festaRepository.findByMatricula(festa.getMatricula());
        if(f == null) return null;
        if(f.getConvidados().contains(p)) return null;
        f.getConvidados().add(p);
        return festaRepository.save(f);
    }

    //DELETE deletar uma festa
    public String delete(Festa festa) {
        Festa f = festaRepository.findByMatricula(festa.getMatricula());
        if(f == null) return "Festa não existe";
        festaRepository.delete(f);
        return "Festa removida com sucesso";
    }
}
