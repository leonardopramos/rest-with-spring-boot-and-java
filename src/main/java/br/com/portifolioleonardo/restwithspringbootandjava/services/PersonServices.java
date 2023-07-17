package br.com.portifolioleonardo.restwithspringbootandjava.services;

import br.com.portifolioleonardo.restwithspringbootandjava.data.vo.v1.PersonVO;
import br.com.portifolioleonardo.restwithspringbootandjava.data.vo.v2.PersonVOV2;
import br.com.portifolioleonardo.restwithspringbootandjava.exceptions.ResourceNotFoundException;
import br.com.portifolioleonardo.restwithspringbootandjava.mapper.Custom.PersonMapper;
import br.com.portifolioleonardo.restwithspringbootandjava.mapper.DozerMapper;
import br.com.portifolioleonardo.restwithspringbootandjava.model.Person;
import br.com.portifolioleonardo.restwithspringbootandjava.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;
    @Autowired
    PersonMapper mapper;

    public List<PersonVO> findAll(){
        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class) ;
    }

    public PersonVO createPerson(PersonVO person) {
        logger.info("Creating one Person");
        var entity = DozerMapper.parseObject(person, Person.class);
        return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
    }
    public PersonVO updatePerson(PersonVO person) {
        logger.info("Updating one Person");

        var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this Id."));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
    }
    public void delete(Long id) {
        logger.info("Deleting one Person");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this Id."));
        repository.delete(entity);
    }
    public PersonVO findById(Long id){
        logger.info("Finding one Person!");
        var entity =  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this Id."));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVOV2 createPersonV2(PersonVOV2 person) {
        logger.info("Creating one Person(v2)");
        var entity = mapper.convertVoTOEntity(person);
        return mapper.convertEntityToVo(repository.save(entity));
    }
}
