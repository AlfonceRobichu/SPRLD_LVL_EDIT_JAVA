package src.gui;

import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;

import src.Input;
import src.RayClass;

public class BoolToogleValue extends GUIelement {
    private boolean value;

    public BoolToogleValue(String name, int xPos, int yPos, boolean initValue){
        super(name,xPos, yPos);
        value = initValue;
    }

    public void toogleValue(){
        value = !value;
    }

    public boolean getValue(){
        return value;
    }


    @Override
    public String toString(){
        return value == true ? "1" : "0";
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
        RayClass.rlj.text.DrawText(""+value, xPos + width - 30, yPos, textSize, Color.WHITE);
    }
}
