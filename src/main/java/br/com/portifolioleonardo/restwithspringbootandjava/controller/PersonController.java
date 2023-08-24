package br.com.portifolioleonardo.restwithspringbootandjava.controller;

import br.com.portifolioleonardo.restwithspringbootandjava.data.vo.v1.PersonVO;
import br.com.portifolioleonardo.restwithspringbootandjava.data.vo.v2.PersonVOV2;
import br.com.portifolioleonardo.restwithspringbootandjava.services.PersonServices;
import br.com.portifolioleonardo.restwithspringbootandjava.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person/v1")
public class PersonController {

    @Autowired
    private PersonServices PersonServices;


    @GetMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON,
                                                MediaType.APPLICATION_XML,
                                                MediaType.APPLICATION_YML})
    public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception{
        return PersonServices.findById(id);
    }
    @GetMapping(produces = {MediaType.APPLICATION_JSON,
                            MediaType.APPLICATION_XML,
                            MediaType.APPLICATION_YML})
    public List<PersonVO> findAll() {
        return PersonServices.findAll();
    }
    @PostMapping(produces = {MediaType.APPLICATION_JSON,
                            MediaType.APPLICATION_XML,
                            MediaType.APPLICATION_YML},
                consumes = {MediaType.APPLICATION_JSON,
                        MediaType.APPLICATION_XML,
                        MediaType.APPLICATION_YML})
    public PersonVO create(@RequestBody PersonVO personVO) throws Exception{
        return PersonServices.createPerson(personVO);
    }
    @PostMapping(value="/v2", produces = {MediaType.APPLICATION_JSON,
                                            MediaType.APPLICATION_XML,
                                            MediaType.APPLICATION_YML},
                                consumes = {MediaType.APPLICATION_JSON,
                                        MediaType.APPLICATION_XML,
                                        MediaType.APPLICATION_YML})
    public PersonVOV2 createV2(@RequestBody PersonVOV2 personVOV2) throws Exception{
        return PersonServices.createPersonV2(personVOV2);
    }
    @PutMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    public PersonVO update(@RequestBody PersonVO personVO) throws Exception{
        return PersonServices.updatePerson(personVO);
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception{
        PersonServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}