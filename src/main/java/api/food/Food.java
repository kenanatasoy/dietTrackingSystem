package api.food;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "foods")
public class Food implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "calorie")
    private Double calorie;

    @Enumerated(EnumType.STRING)
    @Column(name = "measure_type")
    private MeasureType measureType;

    @Column(name = "measure_amount")
    private Double measureAmount;

}