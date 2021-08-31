package api.clientNutrition;

import api.exceptionHandlers.BadRequestException;
import api.exceptionHandlers.EntitiesNotFoundException;
import api.food.Food;
import api.food.FoodService;
import api.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class ClientNutritionServiceImpl implements ClientNutritionService {

    @Autowired
    private ClientNutritionRepository clientNutritionRepository;

    @Autowired
    private FoodService foodService;

    @Autowired
    private PersonService personService;

    @Autowired
    private ClientNutritionMapper clientNutritionMapper;

    @Override
    public List<ClientNutrition> getAllClientNutritions() {

        final Optional<List<ClientNutrition>> clientNutritionList =  Optional.of(this.clientNutritionRepository.findAll());

        if(!clientNutritionList.isPresent() || clientNutritionList.get().isEmpty()){
            throw new EntitiesNotFoundException("Veritabanında hiçbir öğün kaydı yok");
        }
        return this.clientNutritionRepository.findAll();

    }

    @Override
    public ClientNutrition getClientNutritionById(Integer id) {

        final Optional<ClientNutrition> clientNutrition =  this.clientNutritionRepository.findById(id);

        if(!clientNutrition.isPresent()){
            throw new EntityNotFoundException("Öyle bir öğün kaydı yok");
        }

        return clientNutrition.get();
    }

    @Override
    public void deleteClientNutritionById(Integer id) {

        final Boolean isClientNutritionPresentById = this.clientNutritionRepository.existsById(id);

        if (!isClientNutritionPresentById){
            throw new EntityNotFoundException("Öyle bir öğün kaydı yok");
        }

        this.clientNutritionRepository.deleteById(id);
    }

    @Override
    public Double calculateAndGetClientNutritionCalorieAndSaveClientNutrition(ClientNutritionAddDto clientNutritionAddDto) {

        final Boolean isFoodPresentById = this.foodService.getAllFoods().stream().filter(f -> f.getId() == clientNutritionAddDto.getFoodId()).findFirst().isPresent();

        if (!isFoodPresentById){
            throw new EntityNotFoundException("Öyle bir yiyecek yok");
        }

        final Boolean isClientPresentById = this.personService.getAllPersons().stream().filter(f -> f.getId() == clientNutritionAddDto.getClientId()).findFirst().isPresent();

        if (!isClientPresentById){
            throw new EntityNotFoundException("Öyle bir müşteri yok");
        }

        final ClientNutrition newClientNutrition = clientNutritionMapper.convertToClientNutrition(clientNutritionAddDto);

        this.clientNutritionRepository.save(newClientNutrition);

        final Food food = this.foodService.getFoodById(clientNutritionAddDto.getFoodId());

        return (food.getCalorie() / food.getMeasureAmount()) * clientNutritionAddDto.getAmount();
    }

    @Override
    public Double calculateAndGetClientAllCalories(Integer clientId){

        if (!isClientPresentById(clientId)){
            throw new EntityNotFoundException("Öyle bir müşteri yok");
        }

        final Optional<List<ClientNutrition>> clientNutritionList = Optional.of(this.clientNutritionRepository.getByClientId(clientId));

        if(clientNutritionList.get().isEmpty()){
            throw new EntitiesNotFoundException("Bu müşterinin hiç öğün kaydı yok");
        }

        AtomicReference<Double> clientAllCalories = new AtomicReference<>(0.0);

        clientNutritionList.get().stream().forEach(clientNutrition -> {
            Food food = this.foodService.getFoodById(clientNutrition.getFoodId());
            clientAllCalories.updateAndGet(f -> f + ((food.getCalorie() / food.getMeasureAmount()) * clientNutrition.getAmount()));
        });

        return clientAllCalories.get();

    }

    @Override
    public Double calculateAndGetClientCaloriesByDate(Integer clientId, String date) {

        if (!isClientPresentById(clientId)){
            throw new EntityNotFoundException("Öyle bir müşteri yok");
        }

        final Optional<List<ClientNutrition>> clientNutritionList = Optional.of(this.clientNutritionRepository.getByClientId(clientId));

        if(!clientNutritionList.isPresent() || clientNutritionList.get().isEmpty()) {
            throw new BadRequestException("Bu müşterinin hiç öğün kaydı yok");
        }

        AtomicReference<Double> clientDailyCalories = new AtomicReference<>(0.0);

        final DateTimeFormatter dTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final LocalDate lD = LocalDate.parse(date, dTF);

        clientNutritionList.get().stream().filter(cN -> cN.getAddedDateTime().toLocalDate().isEqual(lD)).
            forEach(clientNutrition -> {
            Food food = this.foodService.getFoodById(clientNutrition.getFoodId());
                clientDailyCalories.updateAndGet(f -> f + ((food.getCalorie() / food.getMeasureAmount()) * clientNutrition.getAmount()));
        });

        return clientDailyCalories.get();
    }


    @Override
    public Map<String, Double> calculateAndGetClientCaloriesByFoodAndByOptionalDate(Integer clientId, String date){

        if (!isClientPresentById(clientId)){
            throw new EntityNotFoundException("Öyle bir müşteri yok");
        }

        List<ClientNutrition> clientNutritionList = this.clientNutritionRepository.getByClientId(clientId);

        if(clientNutritionList.isEmpty()) {
            throw new EntitiesNotFoundException("Bu müşterinin hiç öğün kaydı yok");
        }

        if(date != null){
            final DateTimeFormatter dTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            final LocalDate lD = LocalDate.parse(date, dTF);

            clientNutritionList = clientNutritionList.stream().filter(cN -> cN.getAddedDateTime().toLocalDate().isEqual(lD)).collect(Collectors.toList());

            if(clientNutritionList.isEmpty()) {
                throw new EntitiesNotFoundException("Bu tarihte hiç öğün kaydı yok");
            }
        }

        List<Food> foods = this.foodService.getAllFoods();

        Map<String, Double> clientDailyCaloriesByFood = new HashMap<>();

        clientNutritionList.stream().forEach(clientNutrition -> {
            Food foodById = foodService.getFoodById(clientNutrition.getFoodId());
            Double cNFood = clientDailyCaloriesByFood.get(foodById.getName());
            Food food = foods.stream().filter(f -> f.getId().equals(clientNutrition.getFoodId())).findFirst().orElse(null);
            if (cNFood == null){
                clientDailyCaloriesByFood.put(foodById.getName(), ((food.getCalorie() / food.getMeasureAmount()) * clientNutrition.getAmount()) );
            }
            else {
                cNFood += ((food.getCalorie() / food.getMeasureAmount()) * clientNutrition.getAmount());
                clientDailyCaloriesByFood.put(foodById.getName(), cNFood);
            }
        });

        return clientDailyCaloriesByFood;
    }

    /*@Override
    public ClientCaloriesGetDtoByFoodAndDate calculateAndGetClientCaloriesByFoodAndByOptionalDate_2() {
        return this.clientNutritionRepository.calculateAndGetClientCaloriesByFoodAndByOptionalDate_2();
    }*/








    public Boolean isClientPresentById(Integer clientId){
        return this.personService.getAllPersons().stream().filter(f -> f.getId() == clientId).findFirst().isPresent();
    }

}
