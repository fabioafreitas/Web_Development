package br.springboot.ExemploPessoaAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    // CREATE
    @PostMapping
    public Pessoa create(@RequestBody Pessoa pessoa) {
        Pessoa p = pessoaService.save(pessoa);
        return p;
    }



    // RETRIEVE
    @GetMapping("/cpf/{cpf}")
    public Pessoa getPersonByCPF(@PathVariable("cpf") String cpf) {
        return pessoaService.getByCpf(cpf);
    }
    @GetMapping("/nome/{nome}")
    public List<Pessoa> getPersonByNome(@PathVariable("nome") String nome) {
        return pessoaService.getByNome(nome);
    }
    @GetMapping("/idade/{idade}")
    public List<Pessoa> getPersonByIdade(@PathVariable("idade") int idade) {
        return pessoaService.getByIdade(idade);
    }
    @GetMapping
    public List<Pessoa> getAll(){
        return pessoaService.getAll();
    }

    // DELETE
    @DeleteMapping
    public String delete(@RequestBody Pessoa pessoa) {
        boolean b = pessoaService.delete(pessoa);
        if(b) {
            return "removido com sucesso";
        } else {
            return "não foi possível remover";
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Pessoa pessoa) {
        Pessoa p = pessoaService.getByCpf(pessoa.getCpf());
        if(p == null) {
            return new ResponseEntity<>("Produto não existe", HttpStatus.NOT_FOUND);
        }
        p = pessoaService.update(pessoa);
        return new ResponseEntity<>("Alterado com sucesso!", HttpStatus.OK);
    }
}
