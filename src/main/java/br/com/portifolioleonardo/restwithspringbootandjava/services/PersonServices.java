package br.com.portifolioleonardo.restwithspringbootandjava.services;

import br.com.portifolioleonardo.restwithspringbootandjava.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<Person> findAll(){
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Person Person = mockPerson(i);
            persons.add(Person);
        }
        return persons;
    }

    private Person mockPerson(int i) {
        logger.info("Finding all person!");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name" + i);
        person.setLastName("Last name" + i);
        person.setAddress("Some address in brazil");
        person.setGender("Male");
        return person;
    }

    public Person findById(String id){
        logger.info("Finding one person!");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Leonardo");
        person.setLastName("Ramos");
        person.setAddress("Porto Alegre - RS - Brazil");
        person.setGender("Male");
        return person;
    }
}
