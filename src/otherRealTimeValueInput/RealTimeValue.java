package src.otherRealTimeValueInput;

import java.util.Optional;

public interface RealTimeValue {

    Optional<Character> upd();

    boolean isCorrectlyFormed();
}
