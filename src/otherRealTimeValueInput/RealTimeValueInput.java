package src.otherRealTimeValueInput;

import java.util.Optional;

import src.RayClass;

public class RealTimeValueInput {   //patron de conception Singleton
    private static RealTimeValueInput realTimeValueInput;

    private Optional<RealTimeValueInputArea> realTimeValue;
    private String editedValue;

    private RealTimeValueInput(){
        realTimeValue = Optional.empty();
    }

    /**
     * 
     * @param realTimeValue la valeur a modifier
     */
    public void activateRealTimeValueInput(RealTimeValueInputArea realTimeValue){
        if(realTimeValueInput.realTimeValue.isPresent()){
            deactivateRealTimeValueInput();
        }
        realTimeValueInput.realTimeValue = Optional.of(realTimeValue);
        realTimeValueInput.editedValue = realTimeValueInput.realTimeValue.get().toString();
    }

    public void deactivateRealTimeValueInput(){
        if(realTimeValueInput.editedValue == "" ||
            !realTimeValueInput.realTimeValue.get().isCorrectlyFormed(realTimeValueInput.editedValue)) return;

        realTimeValueInput.realTimeValue.get().setValue(realTimeValueInput.editedValue);
        realTimeValueInput.realTimeValue = Optional.empty();
    }

    public boolean isActive(){
        return realTimeValue.isPresent();
    }


    public static RealTimeValueInput getRealTimeValueInput(){
        if(realTimeValueInput == null){
            realTimeValueInput = new RealTimeValueInput();
        }
        return realTimeValueInput;
    }

    


    public void upd(){
        if(RayClass.rlj.core.IsKeyReleased(256)){ //appuye sur retour
        realTimeValueInput.editedValue.substring(0, realTimeValueInput.editedValue.length());    
        }

        else if(RayClass.rlj.core.IsKeyReleased(257)){ //appuye sur entree
            deactivateRealTimeValueInput(); 
        }
        else {    
            realTimeValueInput.editedValue += (char)RayClass.rlj.core.GetCharPressed();
        }
    }


}
