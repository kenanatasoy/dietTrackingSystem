package api.food;

import java.util.List;

public interface FoodService {
    List<Food> getAllFoods();
    Food getFoodById(Integer id);
    Food getFoodByName(String name);
    void addFood(FoodAddDto foodAddDto);
    void deleteFoodById(Integer clientId);
}
