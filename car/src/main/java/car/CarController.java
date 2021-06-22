package car;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;


@RestController
public class CarController {
    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/cars")
    public List<Car> cars() {
        return carService.getCars();
    }

    @GetMapping("/types")
    public Set<String> types() {
        return carService.getCarsBand();
    }
}



