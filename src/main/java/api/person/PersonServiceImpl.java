package api.person;

import api.exceptionHandlers.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    PersonRepository personRepository;

    @Override
    public List<Person> getAllPersons()  {

        final Optional<List<Person>> personList = Optional.of(this.personRepository.findAll());

        if (!personList.isPresent() || personList.get().isEmpty()){
            throw new BadRequestException("Veritabanında hiçbir müşteri yok");
        }

        return personList.get();
    }

    @Override
    public Person getPersonById(Integer id) {
        final Optional<Person> personOptional =  personRepository.findById(id);
        if(!personOptional.isPresent()) {
            throw new EntityNotFoundException("Öyle bir müşteri yok");
        }

        return personOptional.get();

    }

    public void savePerson(Person person) {
        personRepository.save(person);
        //TODO:
    }

    public void updatePerson(Person person) {
        personRepository.save(person);
        //TODO:
    }

    @Override
    public void deletePersonById(Integer id) {

        final Boolean isPersonPresentById = this.personRepository.existsById(id);

        if (!isPersonPresentById){
            throw new EntityNotFoundException("Öyle bir müşteri yok");
        }

        personRepository.deleteById(id);
    }

}
