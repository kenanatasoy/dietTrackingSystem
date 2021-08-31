package api.food;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodMapper {

    FoodMapper FOOD_MAPPER = Mappers.getMapper(FoodMapper.class);

    Food convertToFood(FoodAddDto foodAddDto);

}
