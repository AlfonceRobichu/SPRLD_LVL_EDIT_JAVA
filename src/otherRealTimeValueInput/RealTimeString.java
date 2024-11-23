package src.otherRealTimeValueInput;

import java.util.Optional;

import src.RayClass;

public class RealTimeString extends RealTimeValue{
    String value;

    public RealTimeString(String name, String value){
        super(name);
        this.value = value;
    }

    @Override
    public Optional<Character> upd() {
        return Optional.of((char)RayClass.rlj.core.GetCharPressed());
    }

    @Override
    public boolean isCorrectlyFormed(String s) {
        return true;
    }

    @Override
    public void setValue(String s){
        value = s;
    }

    @Override
    public String toString(){
        return value; 
    }

}
