package br.com.portifolioleonardo.restwithspringbootandjava.services;

import br.com.portifolioleonardo.restwithspringbootandjava.controller.PersonController;
import br.com.portifolioleonardo.restwithspringbootandjava.data.vo.v1.PersonVO;
import br.com.portifolioleonardo.restwithspringbootandjava.data.vo.v2.PersonVOV2;
import br.com.portifolioleonardo.restwithspringbootandjava.exceptions.ResourceNotFoundException;
import br.com.portifolioleonardo.restwithspringbootandjava.mapper.Custom.PersonMapper;
import br.com.portifolioleonardo.restwithspringbootandjava.mapper.DozerMapper;
import br.com.portifolioleonardo.restwithspringbootandjava.model.Person;
import br.com.portifolioleonardo.restwithspringbootandjava.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;
    @Autowired
    PersonMapper mapper;

    public List<PersonVO> findAll() {
        var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class) ;
        persons
                .stream()
                .forEach(p -> {
                    try {
                        p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return persons;
    }

    public PersonVO createPerson(PersonVO person) throws Exception {
        logger.info("Creating one Person");
        var entity = DozerMapper.parseObject(person, Person.class);
        var vo =  DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }
    public PersonVO updatePerson(PersonVO person) throws Exception {
        logger.info("Updating one Person");

        var entity = repository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this Id."));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }
    public void delete(Long id) {
        logger.info("Deleting one Person");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this Id."));
        repository.delete(entity);
    }
    public PersonVO findById(Long id) throws Exception {
        logger.info("Finding one Person!");
        var entity =  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this Id."));
        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVOV2 createPersonV2(PersonVOV2 person) {
        logger.info("Creating one Person(v2)");
        var entity = mapper.convertVoTOEntity(person);
        return mapper.convertEntityToVo(repository.save(entity));
    }
}
