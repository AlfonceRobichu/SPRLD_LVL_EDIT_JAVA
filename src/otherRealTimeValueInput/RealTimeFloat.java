package src.otherRealTimeValueInput;

import java.util.Optional;

public class RealTimeFloat implements RealTimeValue{
    private float value;

    public Optional<Character> upd(){
        return Optional.empty();
    }

    @Override
    public boolean isCorrectlyFormed() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isCorrectlyFormed'");
    }


}
