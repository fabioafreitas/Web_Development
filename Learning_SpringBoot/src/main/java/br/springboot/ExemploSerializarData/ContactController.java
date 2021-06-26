package br.springboot.ExemploSerializarData;

import org.springframework.web.bind.annotation.*;

import java.util.Date;


//localhost:8080/data/criar

@CrossOrigin("*")
@RestController
@RequestMapping("/datas")
public class ContactController {

    @PostMapping("/date")
    public Contact date(@RequestBody Contact date) {
        return date;
    }

    @PostMapping("/localdate")
    public ContactWithJavaUtilDate localDate(@RequestBody ContactWithJavaUtilDate date) {
        return date;
    }

    @PostMapping("/data2")
    public Contact2 data2(@RequestBody Contact2 date) {
        return date;
    }

}
