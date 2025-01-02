package src.gui;

import java.util.ArrayList;
import java.util.List;

import com.raylib.java.core.Color;

import src.RayClass;
import src.otherRealTimeValueInput.Bool;
import src.otherRealTimeValueInput.BoolToogleValueArea;
import src.otherRealTimeValueInput.EditableValueArea;
import src.otherRealTimeValueInput.RealTimeValue;
import src.otherRealTimeValueInput.RealTimeValueInputArea;

public class ActorEditableOptions extends GUIelement {

    /*
    element du GUI, permet de modifier certains attributs de l'acteur selectionne
     lorsqu'un acteur est selectionne, ses attributs modifiables seront injectes dans
     une liste qui peut etre interagi afin de modifier ces attributs
    */

    /*
    les attributs sont stockes dans l'acteur comme RealTimeValue et sont mis
    dans des RealTimeValueInputArea lors de leur transfert dans cette classe
     */

    private List<EditableValueArea> actorOptions;


    public ActorEditableOptions(List<RealTimeValue> actorOptions){
        int yPosOption = 500;
        int yDecalage = 30;
        super("actor options", 0, yPosOption);
        this.actorOptions = new ArrayList<>();
        for(RealTimeValue option : actorOptions){
            yPosOption += yDecalage;
            this.actorOptions.add(addButton(yPosOption, option));
        }
    }

    private EditableValueArea addButton(int yPosOption, RealTimeValue option){
        if( option instanceof Bool ){
            return new BoolToogleValueArea(option.getName(), 0, yPosOption, (Bool)option);
        }
        else{
            return new RealTimeValueInputArea(0, yPosOption, option);
        }

    }

    @Override
    public void upd() {
        for(EditableValueArea option : actorOptions){
            option.upd();
        }
    }


    @Override
    public void draw() {
        RayClass.rlj.text.DrawText(name, xPos, yPos - height, textSize, Color.WHITE);
        for(EditableValueArea option : actorOptions){
            option.draw();
        }
    }


}
