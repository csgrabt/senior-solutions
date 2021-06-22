package car;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Data
@Service
public class CarService {

    private List<Car> cars = Arrays.asList(
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
            new Car("Opel", "AstraII", 100, Status.BAD,
                    List.of(
                            new KmState(LocalDate.of(2000, 10, 10), 1125)
                    )
            )
    );
}
