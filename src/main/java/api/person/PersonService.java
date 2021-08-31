package api.person;

import java.util.List;

public interface PersonService {

    List<Person> getAllPersons();

    Person getPersonById(Integer id);

    void savePerson(Person person);

    void updatePerson(Person person);

    void deletePersonById( Integer id);

}
