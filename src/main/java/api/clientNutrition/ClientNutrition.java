package api.clientNutrition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client_nutritions")
@Entity
public class ClientNutrition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "client_id")
    private Integer clientId;

    @Column(name = "food_id")
    private Integer foodId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "added_date_time")
    private LocalDateTime addedDateTime = LocalDateTime.now();

}
