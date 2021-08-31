package api.clientNutrition;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientNutritionRepository extends JpaRepository<ClientNutrition, Integer> {

    List<ClientNutrition> getByClientId(Integer clientId);

/*    @Query("select new com.diettracker.dietapp.clientNutrition.ClientCaloriesGetDtoByFoodAndDate" +
            "(c.id, sum((f.calorie / f.measureAmount) * cN.amount)) from ClientNutrition cN join Client c join Food f" +
            " where cN.clientId = c.id or cN.foodId = f.id group by c.id")
    ClientCaloriesGetDtoByFoodAndDate calculateAndGetClientCaloriesByFoodAndByOptionalDate_2();*/


}

