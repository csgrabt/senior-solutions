package car;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class Car {
    private final String brand;
    private final String type;
    private int age;
    private Status status;
    private List<KmState> kmStatesList;

}
