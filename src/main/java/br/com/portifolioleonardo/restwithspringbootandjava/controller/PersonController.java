package br.com.portifolioleonardo.restwithspringbootandjava.controller;

import br.com.portifolioleonardo.restwithspringbootandjava.data.vo.v1.PersonVO;
import br.com.portifolioleonardo.restwithspringbootandjava.model.Person;
import br.com.portifolioleonardo.restwithspringbootandjava.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices PersonServices;
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception{
        return PersonServices.findById(id);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonVO> findAll() {
        return PersonServices.findAll();
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO create(@RequestBody PersonVO personVO) throws Exception{
        return PersonServices.createPerson(personVO);
    }
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO update(@RequestBody PersonVO personVO) throws Exception{
        return PersonServices.updatePerson(personVO);
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception{
        PersonServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}