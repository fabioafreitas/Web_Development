package br.springboot.ExemploHeranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/arvores")
public class ArvoreController {

    @Autowired
    private ArvoreRepository arvoreRepository;

    @PostMapping
    public Arvore save(@RequestBody Arvore arvore) {
        return arvoreRepository.save(arvore);
    }

    @GetMapping
    public List<Arvore> getAll() {
        return arvoreRepository.findAll();
    }
}
