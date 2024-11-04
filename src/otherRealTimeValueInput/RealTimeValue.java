package src.otherRealTimeValueInput;

import java.util.Optional;

public interface RealTimeValue {

    Optional<Character> upd();

    boolean isCorrectlyFormed(String s);

    void setValue(String s);
    
    @Override
    String toString();
}
