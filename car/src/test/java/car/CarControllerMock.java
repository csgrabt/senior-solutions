package car;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarControllerMock {
    @Mock
    CarService carService;
    @InjectMocks
    CarController carController;

    @Test
    void carsTest() {
        when(carService.getCars()).thenReturn(Arrays.asList(
                new Car("Opel", "Astra", 1, Status.NORMAL,
                        List.of(
                                new KmState(LocalDate.of(2000, 10, 10), 25)
                        )
                )));

        assertEquals(1, carController.cars().size());
    }

    @Test
    void typeTest() {
        when(carService.getCars()).thenReturn(Arrays.asList(
                new Car("Opel", "Astra", 1, Status.NORMAL,
                        List.of(
                                new KmState(LocalDate.of(2000, 10, 10), 25)
                        )
                )));

        Set<String> result = new HashSet<>();

        result.add("Opel - Astra");

        assertEquals(result, carController.types());
    }

}
