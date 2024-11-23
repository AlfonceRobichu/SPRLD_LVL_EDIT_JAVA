package src.gui;

import java.util.ArrayList;
import java.util.List;

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

    private List<RealTimeValueInputArea> actorOptions;


    public ActorEditableOptions(List<RealTimeValue> actorOptions){
        int yPos = 500;
        int yDecalage = 30;
        super("actor options", 0, yPos);
        this.actorOptions = new ArrayList<>();
        for(RealTimeValue option : actorOptions){
            yPos += yDecalage;
            this.actorOptions.add(new RealTimeValueInputArea(0, yPos, option));
        }


    }


    @Override
    public void upd() {
        for(RealTimeValueInputArea option : actorOptions){
            option.upd();
        }
    }


    @Override
    public void draw() {
        for(RealTimeValueInputArea option : actorOptions){
            option.draw();
        }
    }


}
