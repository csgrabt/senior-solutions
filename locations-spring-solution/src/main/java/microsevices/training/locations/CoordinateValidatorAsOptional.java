package microsevices.training.locations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class CoordinateValidatorAsOptional implements ConstraintValidator<Coordinate, Optional<Double>> {
    private Type type;


    @Override
    public void initialize(Coordinate constraintAnnotation) {
        this.type = constraintAnnotation.type();
    }

    @Override
    public boolean isValid(Optional<Double> aDouble, ConstraintValidatorContext constraintValidatorContext) {

        if (!aDouble.isEmpty()) {

            Double a = aDouble.get();

            return this.type.getMin() <= a && this.type.getMax() >= a;

        } else {
            return true;
        }
    }
}
