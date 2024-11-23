package src.gui;

import java.util.List;

import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;

import src.Input;
import src.RayClass;
import src.gui_commands.CommandLoadFile;
import src.gui_commands.CommandNewFile;
import src.gui_commands.CommandSaveFile;
import src.gui_commands.CommandSaveFileAs;

public class SelectionCascadeAction extends GUIelement{
    private boolean debale;

    private GUIcommandElt[] actions;

    public SelectionCascadeAction(String name, int xPos, int yPos, List<GUIcommandElt> actions){
        super(name, xPos, yPos);
        
        this.actions = new GUIcommandElt[actions.size()];
        int i = 0;
        for (GUIcommandElt action : actions) {
            this.actions[i++] = action;
        }

        debale = false;
    }

    public static SelectionCascadeAction fichiersAction(){
        return new SelectionCascadeAction("Fichier", 0, 0,
         List.of(
            new GUIcommandElt("Nouveau Fichier",    new CommandNewFile()),
            new GUIcommandElt("Ouvrir",             new CommandLoadFile()),
            new GUIcommandElt("Enregistrer",        new CommandSaveFile()),
            new GUIcommandElt("Enregistrer sous",   new CommandSaveFileAs())
         ));
    }
    

    public void upd(){
        if(debale) GUI.mousePressedinGUItrue();
        if(!Input.isMouseLeftPressed()) return;
        mouseEvent();
    }


    private void mouseEvent(){
        Rectangle selRect = new Rectangle(xPos, yPos, width, height);
        if(RayClass.rlj.shapes.CheckCollisionPointRec(Input.getCursorPos(), selRect)){  //clique sur l'en-tete de la cascade
            debale = !debale;
            GUI.mousePressedinGUItrue();
            return;
        }
        if(!debale) return;

        debale = false;

        int yOptioni = yPos;
        int xOptions = xPos + width;
        int i = 0;
        for(i = 0; i < actions.length; i++){
            actions[i].mouseEvent(xOptions, yOptioni, width, height);
            yOptioni += height;
        }
    }

    public void draw(){
        RayClass.rlj.shapes.DrawRectangle(xPos ,yPos, width, height, RayClass.rlj.textures.GetColor(0x303030FF));
        RayClass.rlj.shapes.DrawRectangleLines(xPos ,yPos, width, height, Color.LIGHTGRAY);
        RayClass.rlj.text.DrawText(name, xPos, yPos, textSize, Color.WHITE);
        if(!debale) return;
        
        int yOptioni = yPos;
        int xOptions = xPos + width;
        for(int i = 0; i < actions.length; i++){
            actions[i].draw(xOptions, yOptioni, width, height, textSize);
            yOptioni += height;
        }
    }

}
