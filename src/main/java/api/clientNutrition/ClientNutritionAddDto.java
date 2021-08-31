package api.clientNutrition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientNutritionAddDto {
    private Integer clientId;
    private Integer foodId;
    private Double amount;
}
