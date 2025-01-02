package src.otherRealTimeValueInput;

import src.gui.GUIelement;

public abstract class EditableValueArea extends GUIelement{

    public EditableValueArea(String name, int xPos, int yPos){
        super(name, xPos, yPos);
    }

    public abstract void upd();
    
    public abstract void draw();
}
