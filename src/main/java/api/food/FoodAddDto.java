package api.food;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodAddDto {
    private String name;
    private Double calorie;
    private MeasureType measureType;
    private Double measureAmount;
}
