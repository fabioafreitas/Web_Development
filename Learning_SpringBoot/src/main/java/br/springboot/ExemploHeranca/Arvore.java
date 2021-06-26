package br.springboot.ExemploHeranca;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;


@Document(collection = "arvore")
public class Arvore extends Planta{

    @Id
    private String id;
    private int idade;

    public Arvore(String nome, int idade) {
        super(nome);
        this.idade = idade;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arvore arvore = (Arvore) o;
        return idade == arvore.idade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idade);
    }
}
