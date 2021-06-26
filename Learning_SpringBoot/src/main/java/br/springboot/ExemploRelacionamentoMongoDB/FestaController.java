package br.springboot.ExemploRelacionamentoMongoDB;

import br.springboot.ExemploPessoaAPI.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/festas")
public class FestaController {

    @Autowired
    private FestaService festaService;

    @GetMapping
    public List<Festa> getAll() {
        return festaService.getAll();
    }

    @GetMapping("/{matricula}")
    public Festa getByMatricula(@PathVariable("matricula") String matricula) {
        return festaService.getByMatricula(matricula);
    }

    @PostMapping
    public Festa save(@RequestBody Festa festa) {
        return festaService.save(festa);
    }

    @DeleteMapping
    public String delete(@RequestBody Festa festa) {
        return festaService.delete(festa);
    }

    @PutMapping("/convidar/{matricula}/{cpf}")
    public Festa convidar(@PathVariable("matricula") String matricula, @PathVariable("cpf") String cpf) {
        return festaService.convidar(
                new Festa(matricula, null),
                new Pessoa(cpf, null, 0));
    }

    @PutMapping("/desconvidar/{matricula}/{cpf}")
    public Festa desconvidar(@PathVariable("matricula") String matricula, @PathVariable("cpf") String cpf) {
        return festaService.desconvidar(
                new Festa(matricula, null),
                new Pessoa(cpf, null, 0));
    }


}
