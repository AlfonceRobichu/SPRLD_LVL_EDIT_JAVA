package src.gui;

import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;
import src.gui_commands.Command;

import src.Input;
import src.RayClass;

public class GUIcommandElt {
    private Command command;
    private String name;

    public GUIcommandElt(String name, Command command){
        this.name = name;
        this.command = command;
    }

    public void mouseEvent(int xPos, int yPos, int width, int height){
        Rectangle selRect = new Rectangle(xPos, yPos, width, height);
        if(RayClass.rlj.shapes.CheckCollisionPointRec(Input.getCursorPos(), selRect)){
            command.execute();
        }
        
    }

    public void draw(int xPos, int yPos, int width, int height, int textSize) {
        RayClass.rlj.shapes.DrawRectangle(xPos , yPos, width, height, RayClass.rlj.textures.GetColor(0x303030FF));
        RayClass.rlj.shapes.DrawRectangleLines(xPos ,yPos, width, height, Color.LIGHTGRAY);
        RayClass.rlj.text.DrawText(name, xPos, yPos, textSize, Color.WHITE);
    }

}
