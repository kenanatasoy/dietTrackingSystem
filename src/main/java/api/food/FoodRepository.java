package api.food;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Integer> {

    Food getById(Integer id);
    Food getByName(String name);


}
