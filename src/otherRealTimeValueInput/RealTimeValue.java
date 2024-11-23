package src.otherRealTimeValueInput;

import java.util.Optional;

public abstract class RealTimeValue {

    private final String name;

    protected RealTimeValue(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public abstract Optional<Character> upd();

    public abstract boolean isCorrectlyFormed(String s);

    public abstract void setValue(String s);
    
    public abstract String toString();
}
