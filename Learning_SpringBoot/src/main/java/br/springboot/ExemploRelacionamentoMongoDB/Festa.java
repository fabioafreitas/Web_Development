package br.springboot.ExemploRelacionamentoMongoDB;

import br.springboot.ExemploPessoaAPI.Pessoa;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document(collection = "festa")
public class Festa {

    @Id
    private String id;
    private String matricula;
    private List<Pessoa> convidados;

    public Festa(String matricula, List<Pessoa> convidados) {
        this.matricula = matricula;
        this.convidados = convidados;
    }

    public List<Pessoa> getConvidados() {
        return convidados;
    }

    public void setConvidados(List<Pessoa> convidados) {
        this.convidados = convidados;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Festa festa = (Festa) o;
        return Objects.equals(matricula, festa.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
}
