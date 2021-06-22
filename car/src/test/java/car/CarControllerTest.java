package car;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CarControllerTest {
    @Autowired
    CarController carController;

    @Test
    void cars() {
        List<Car> cars = carController.cars();

        assertEquals(3, cars.size());
    }

    @Test
    void types() {
        Set<String> types = carController.types();
        Set<String> types2 = new HashSet<>();
        types2.add("Opel - Astra");
        types2.add("Opel - AstraII");

        assertEquals(types2, types);


    }
}