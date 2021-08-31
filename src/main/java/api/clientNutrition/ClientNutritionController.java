package api.clientNutrition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientnutritions/")
public class ClientNutritionController {

    @Autowired
    private ClientNutritionService clientNutritionService;

    @GetMapping("getAllClientNutritions")
    public List<ClientNutrition> getAllClientNutritions(){
        return this.clientNutritionService.getAllClientNutritions();
    }

    @GetMapping("getClientNutritionById")
    public ClientNutrition getClientNutritionById(Integer id){
        return this.clientNutritionService.getClientNutritionById(id);
    }

    @DeleteMapping("deleteClientNutritionById")
    public void deleteClientNutritionById(Integer id){
        this.clientNutritionService.deleteClientNutritionById(id);
    }

    @PostMapping("calculateAndGetClientNutritionCalorieAndSaveClientNutrition")
    public Double calculateAndGetClientNutritionCalorieAndSaveClientNutrition(@RequestBody ClientNutritionAddDto clientNutritionAddDto){
        return this.clientNutritionService.calculateAndGetClientNutritionCalorieAndSaveClientNutrition(clientNutritionAddDto);
    }

    @GetMapping("calculateAndGetClientAllCalories")
    public Double calculateAndGetClientAllCalories(Integer clientId){
        return this.clientNutritionService.calculateAndGetClientAllCalories(clientId);
    }

    @GetMapping("calculateAndGetClientDailyCalories")
    public Double calculateAndGetClientCaloriesByDate(Integer clientId, String date){
        return this.clientNutritionService.calculateAndGetClientCaloriesByDate(clientId, date);
    }

    @GetMapping("calculateAndGetClientDailyCaloriesByFood")
    public Map<String, Double> calculateAndGetClientCaloriesByFoodAndByOptionalDate(Integer clientId, String date){
        return this.clientNutritionService.calculateAndGetClientCaloriesByFoodAndByOptionalDate(clientId, date);
    }

    /*@GetMapping("calculateAndGetClientCaloriesByFoodAndByOptionalDate_2")
    public ClientCaloriesGetDtoByFoodAndDate calculateAndGetClientCaloriesByFoodAndByOptionalDate_2()
    {
        return this.clientNutritionService.calculateAndGetClientCaloriesByFoodAndByOptionalDate_2();
    }*/


}
