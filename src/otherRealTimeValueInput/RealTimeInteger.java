package src.otherRealTimeValueInput;

import java.util.Optional;

import src.RayClass;

public class RealTimeInteger implements RealTimeValue{
    private Integer value;

    public RealTimeInteger(){
        value = 0;
    }
    
    public RealTimeInteger(int value){
        this.value = value;
    }


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
    public boolean isCorrectlyFormed(String s) {
        // TODO Auto-generated method stub
        return s.matches("(-)?[0-9]+");
    }
    
    @Override
    public void setValue(String s){
        value = Integer.parseInt(s);
    }

    @Override
    public String toString(){
        return "" + value; 
    }
}
