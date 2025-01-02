package src.otherRealTimeValueInput;

import java.util.Optional;

public class Bool extends RealTimeValue{
    public boolean bool;

    public Bool(String name, Boolean bool){
        super(name);
        this.bool = bool;
    }

    @Override
    public Optional<Character> upd() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'upd'");
    }

    @Override
    public boolean isCorrectlyFormed(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isCorrectlyFormed'");
    }

    @Override
    public void setValue(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setValue'");
    }

    @Override
    public String toString() {
        return bool ? "True" : "False"; 
    }

    public String toStringFile(){
        return bool ? "1" : "0";
    }

}
