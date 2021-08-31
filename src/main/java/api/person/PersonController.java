package api.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients/")
public class PersonController {


    @Autowired
    private PersonServiceImpl personServiceImpl;

    @GetMapping("person")
    public List<Person>  getPersons()  {
        return personServiceImpl.getAllPersons();
    }

    @GetMapping("person/{id}")
    public Person getPersonById(@PathVariable Integer id) {
       return personServiceImpl.getPersonById(id);
    }

    @PostMapping("person")
    public void savePerson(@RequestBody Person person) {
        personServiceImpl.savePerson(person);
    }

    @PutMapping("person")
    public void updatePerson(@RequestBody Person person) {
        personServiceImpl.updatePerson(person);
    }

    @DeleteMapping("person/{id}")
    public void deletePerson(@PathVariable  Integer id) {
        personServiceImpl.deletePersonById(id);
    }

}
