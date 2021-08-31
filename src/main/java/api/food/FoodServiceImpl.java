package api.food;

import api.exceptionHandlers.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService{

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public List<Food> getAllFoods() {

        final Optional<List<Food>> foodList =  Optional.of(this.foodRepository.findAll());

        if(!foodList.isPresent() || foodList.get().isEmpty()){
            throw new BadRequestException("Veritabanında hiçbir yiyecek yok");
        }

        return foodList.get();
    }

    @Override
    public Food getFoodById(Integer id) {

        final Optional<Food> food = this.foodRepository.findById(id);

        if (!food.isPresent()){
            throw new EntityNotFoundException("Öyle bir yiyecek yok");
        }

        return food.get();
    }

    @Override
    public Food getFoodByName(String name){

        name = name.toUpperCase(new Locale("tr", "TR"));
        final Optional<Food> food = Optional.of(this.foodRepository.getByName(name));

        if (!food.isPresent()){
            throw new EntityNotFoundException("Öyle bir yiyecek yok");
        }

        return food.get();
    }

    @Override
    public void addFood(FoodAddDto foodAddDto) {

        foodAddDto.setName(foodAddDto.getName().toUpperCase(new Locale("tr", "TR")));

        final Food newFood = FoodMapper.FOOD_MAPPER.convertToFood(foodAddDto);

        Boolean isFoodPresentByName = this.foodRepository.findAll().stream().filter(f -> f.getName().equals(newFood.getName())).findFirst().isPresent();

        if (isFoodPresentByName){
            throw new BadRequestException("Bu yiyecek zaten mevcut");
        }

        this.foodRepository.save(newFood);
    }

    @Override
    public void deleteFoodById(Integer id) {

        Boolean isFoodPresentById = this.foodRepository.existsById(id);

        if (!isFoodPresentById){
            throw new EntityNotFoundException("Öyle bir yiyecek yok");
        }

        this.foodRepository.deleteById(id);
    }
}
