package api.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods/")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("getAllClients")
    public List<Food> getAllFoods() {
        return this.foodService.getAllFoods();
    }

    @GetMapping("getFoodById")
    public Food getFoodById(Integer id){
        return this.foodService.getFoodById(id);
    }

    @GetMapping("getFoodByName")
    public Food getFoodByName(String name){
        return this.foodService.getFoodByName(name);
    }

    @PostMapping("add")
    public void addFood(@RequestBody FoodAddDto foodAddDto) {
        this.foodService.addFood(foodAddDto);
    }

    @DeleteMapping("deleteFoodById")
    public void deleteFoodById(Integer clientId) {
        this.foodService.deleteFoodById(clientId);
    }

}
