package api.person;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="persons")
@Data
public class Person {

    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String emailAddress;
    private LocalDateTime registrationDateTime = LocalDateTime.now();

}
