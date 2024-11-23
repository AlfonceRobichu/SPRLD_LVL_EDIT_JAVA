package src.gui;

import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;

import src.Input;
import src.RayClass;

public class SelectionCascadeElt{
    private final String value;

    public SelectionCascadeElt(String value){
        this.value = value;
    }

    public boolean mouseEvent(int xPos, int yPos, int width, int height){
        Rectangle selRect = new Rectangle(xPos, yPos, width, height);
        return RayClass.rlj.shapes.CheckCollisionPointRec(Input.getCursorPos(), selRect);
    }

    public String getValue(){
        return value;
    }

    public void  draw(int xPos, int yPos, int width, int height, int textSize){
        RayClass.rlj.shapes.DrawRectangle(xPos , yPos, width, height, RayClass.rlj.textures.GetColor(0x303030FF));
        RayClass.rlj.shapes.DrawRectangleLines( xPos ,yPos, width, height, Color.LIGHTGRAY);
        RayClass.rlj.text.DrawText(value, xPos, yPos, textSize, Color.WHITE);
    }


}
