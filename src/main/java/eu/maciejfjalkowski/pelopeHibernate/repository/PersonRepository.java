package eu.maciejfjalkowski.pelopeHibernate.repository;

import eu.maciejfjalkowski.pelopeHibernate.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Long>{
}
