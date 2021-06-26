package br.springboot.ExemploSerializarData;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Contact2 {

    @JsonFormat(pattern="dd-MM-yyyy")
    private Date data;
}
