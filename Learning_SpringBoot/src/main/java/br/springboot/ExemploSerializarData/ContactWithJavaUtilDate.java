package br.springboot.ExemploSerializarData;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ContactWithJavaUtilDate {

    // other fields

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastUpdate;

    // standard getters and setters
}