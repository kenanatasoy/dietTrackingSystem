package api.clientNutrition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientCaloriesGetDtoByFoodAndDate {

    //Integer id;
    Integer clientId;
    //String foodName;
    Long calories;

}
