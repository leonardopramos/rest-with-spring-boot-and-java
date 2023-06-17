package br.com.portifolioleonardo.restwithspringbootandjava.controller;

import br.com.portifolioleonardo.restwithspringbootandjava.model.Person;
import br.com.portifolioleonardo.restwithspringbootandjava.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private PersonServices personServices;
    @RequestMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    public Person findById(@PathVariable(value = "id") String id) throws Exception{
        return personServices.findById(id);
    }
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    public List<Person> findAll() {
        return personServices.findAll();
    }
}
