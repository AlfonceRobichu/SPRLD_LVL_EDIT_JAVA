package src.otherRealTimeValueInput;

import java.util.Optional;

import src.RayClass;

public class RealTimeInteger implements RealTimeValue{
    private Integer value;


    public Optional<Character> upd(){
        int keyChar = RayClass.rlj.core.GetCharPressed();
        
        if(keyChar >= 48 && keyChar <= 57){
            return Optional.of(((char)keyChar));
        }

        else if(keyChar == 45){ //appuye sur '-'
            return Optional.of('-');
        }
        return Optional.empty();
    }


    @Override
    public boolean isCorrectlyFormed() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isCorrectlyFormed'");
    }

}
