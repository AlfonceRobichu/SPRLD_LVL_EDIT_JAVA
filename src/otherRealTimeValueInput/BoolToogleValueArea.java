package src.otherRealTimeValueInput;

import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;

import src.Input;
import src.RayClass;

public class BoolToogleValueArea extends EditableValueArea {
    private Bool value;

    public BoolToogleValueArea(String name, int xPos, int yPos, Bool initValue){
        super(name,xPos, yPos);
        value = initValue;
    }

    public void toogleValue(){
        value.bool = !value.bool;
    }

    public Bool getValue(){
        return value;
    }

    private boolean isMouseClickedOnArea(){
        Rectangle area = new Rectangle(xPos, yPos, width, height);
        return Input.isMouseLeftPressed() && (RayClass.rlj.shapes.CheckCollisionPointRec(Input.getCursorPos(), area));
    }

    @Override
    public void upd() {
        if(isMouseClickedOnArea()){
            toogleValue();
        }
        
    }

    @Override
    public void draw() {
        RayClass.rlj.shapes.DrawRectangle(xPos , yPos, width, height, RayClass.rlj.textures.GetColor(0x303030FF));
        RayClass.rlj.shapes.DrawRectangleLines( xPos ,yPos, width, height, Color.LIGHTGRAY);
        RayClass.rlj.text.DrawText(name, xPos, yPos, textSize, Color.WHITE);
        RayClass.rlj.text.DrawText(value.toString(), xPos + width - 30, yPos, textSize, Color.WHITE);
    }
}
