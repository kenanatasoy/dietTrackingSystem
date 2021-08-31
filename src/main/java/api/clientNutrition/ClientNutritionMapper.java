package api.clientNutrition;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientNutritionMapper {

    //ClientNutritionMapper CLIENT_NUTRITION_MAPPER = Mappers.getMapper(ClientNutritionMapper.class);

    ClientNutrition convertToClientNutrition(ClientNutritionAddDto clientNutritionAddDto);

    /*@Mapping(source = "clientNutrition.id", target = "id")
    @Mapping(source = "food.name", target = "foodName")
    @Mapping(source = "client.id", target = "clientId")
    ClientCaloriesGetDtoByFoodAndDate from(ClientNutrition clientNutrition, Food food, Client client);*/


}
