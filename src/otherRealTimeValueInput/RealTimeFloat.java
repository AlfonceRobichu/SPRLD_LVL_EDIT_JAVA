package src.otherRealTimeValueInput;

import java.util.Optional;

import src.RayClass;

public class RealTimeFloat implements RealTimeValue{
    private float value;

    public RealTimeFloat(){
        value = 0.0f;
    }

    public RealTimeFloat(float value){
        this.value = value;
    }


    public Optional<Character> upd(){
        return Optional.of((char)RayClass.rlj.core.GetCharPressed());
    }

    @Override
    public boolean isCorrectlyFormed(String s) {
        // TODO Auto-generated method stub
        return s.matches("(-)?[0-9]+([.][0-9]+)?");
    }

    @Override
    public void setValue(String s){
        value = Float.parseFloat(s);
    }

    @Override
    public String toString(){
        return "" + value; 
    }

}
