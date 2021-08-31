package api.clientNutrition;

import java.util.List;
import java.util.Map;

public interface ClientNutritionService {
    List<ClientNutrition> getAllClientNutritions();
    ClientNutrition getClientNutritionById(Integer id);
    void deleteClientNutritionById(Integer clientNutritionId);
    Double calculateAndGetClientNutritionCalorieAndSaveClientNutrition(ClientNutritionAddDto clientNutritionAddDto);
    Double calculateAndGetClientAllCalories(Integer clientId);
    Double calculateAndGetClientCaloriesByDate(Integer clientId, String date);
    Map<String, Double> calculateAndGetClientCaloriesByFoodAndByOptionalDate(Integer clientId, String date);
    /*ClientCaloriesGetDtoByFoodAndDate calculateAndGetClientCaloriesByFoodAndByOptionalDate_2();*/
}
