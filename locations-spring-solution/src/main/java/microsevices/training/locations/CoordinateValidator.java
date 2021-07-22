package microsevices.training.locations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;


public class CoordinateValidator implements ConstraintValidator<Coordinate, Double> {

    private Type type;

    @Override
    public boolean isValid(Double aDouble, ConstraintValidatorContext constraintValidatorContext) {
        return this.type.getMax() >= aDouble && this.type.getMin() <= aDouble;
    }

    @Override
    public void initialize(Coordinate constraintAnnotation) {
        this.type = constraintAnnotation.type();
    }
}
