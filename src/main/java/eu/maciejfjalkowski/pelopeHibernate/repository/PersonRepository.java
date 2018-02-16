package eu.maciejfjalkowski.pelopeHibernate.repository;

import eu.maciejfjalkowski.pelopeHibernate.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person,Long>{
    List<Person> findByNameIgnoreCase(String name);
    List<Person> findBySurnameIgnoreCase(String surname);
}
