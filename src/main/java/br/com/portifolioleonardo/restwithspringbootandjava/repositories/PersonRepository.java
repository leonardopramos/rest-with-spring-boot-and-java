package br.com.portifolioleonardo.restwithspringbootandjava.repositories;
import br.com.portifolioleonardo.restwithspringbootandjava.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long>{}