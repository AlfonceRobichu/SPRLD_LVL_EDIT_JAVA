package src.otherRealTimeValueInput;

import java.util.Optional;

public class RealTimeValueInput {   //patron de conception Singleton
    private static RealTimeValueInput realTimeValueInput;

    private Optional<RealTimeValue> realTimeValue;
    private String editedValue; //valeur pendant l'edition
    private String ogValue;     //valeur avant l'edition

    private RealTimeValueInput(){
        realTimeValue = Optional.empty();
    }

    public void activateRealTimeValueInputOn(RealTimeValue realTimeValue){
        realTimeValueInput.realTimeValue = Optional.of(realTimeValue);
    }

    public void deactivateRealTimeValueInput(){
        realTimeValueInput.realTimeValue = Optional.empty();
    }

    public static RealTimeValueInput getRealTimeValueInput(){
        if(realTimeValueInput == null){
            realTimeValueInput = new RealTimeValueInput();
        }
        return realTimeValueInput;
    }

    public static void upd(){
        realTimeValueInput.realTimeValue.ifPresent((value) -> {
            value.upd().ifPresent( (character) -> {
                realTimeValueInput.editedValue += "" + character;
            }
            );
        });
    }


}
