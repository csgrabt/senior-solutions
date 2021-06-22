package car;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Service
public class CarService {

    private List<Car> cars = Collections.synchronizedList(Arrays.asList(
            new Car("Opel", "Astra", 1, Status.NORMAL,
                    List.of(
                            new KmState(LocalDate.of(2000, 10, 10), 25)
                    )
            ),
            new Car("Opel", "Astra", 10, Status.PERFECT,
                    List.of(
                            new KmState(LocalDate.of(2000, 10, 10), 125)
                    )
            ),
            new Car("Trabant", "AstraII", 100, Status.BAD,
                    List.of(
                            new KmState(LocalDate.of(2000, 10, 10), 1125)
                    )
            )
    ));

    public Set<String> getCarsBand() {
        return cars
                .stream()
                .map(Car::getBrand)
                .collect(Collectors.toSet());
    }


}
