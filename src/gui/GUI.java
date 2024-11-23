package src.gui;


import java.util.ArrayList;

import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;

import src.Input;
import src.RayClass;
import src.Screen;
import src.TxtrStrg;
import src.selectionTile.SelectionTileColl;
import src.selectionTile.SelectionTileGraphic;

public class GUI {
    public static final int 
    MODE_EDIT_ACTEUR = 0, 
    MODE_EDIT_TILEGRAPHIQUE = 1,
    MODE_EDIT_TILECOLLISION = 2;
    public static String currLvl = "default.slf";
    private static boolean mousePressedinGUI;
    public static Rectangle bgRect = new Rectangle(0, 0, 300, Screen.fullscreenHeight);
    public static SelectionCascadeEntite entitySel;
    public static SelectionCascadeAction actionfichierSel;
    public static SelectionCascadeEntite acteurSel;
    public static SelectionTileGraphic tileGraphicSel;
    public static SelectionTileColl tileCollSel;
    public static SelectionCascadeEntite currMusic;
    public static SelectionCascadeEntite currBg;
    public static SelectionCascadeEntite currFg;
    public static int currLayer = 1;
    private static int currRot = 0; 
    public static final int DRAW_OPAQUE = 0, DRAW_TRANSPARENT = 1;
    public static int drawOtherEntities = DRAW_OPAQUE;
    public static boolean placement_libre = true;
    public static ActorEditableOptions actorEditableOptions;

    public GUI(){
        tileGraphicSel = new SelectionTileGraphic();
        tileCollSel = new SelectionTileColl();
        currMusic = SelectionCascadeEntite.selectionCascadeMusique();
        actionfichierSel = SelectionCascadeAction.fichiersAction();
        
        entitySel = SelectionCascadeEntite.selectionCascadeEntites();
        acteurSel = SelectionCascadeEntite.selectionCascadeActeurs();
        currBg = SelectionCascadeEntite.selectionCascadeArrierePlan();
        currFg = SelectionCascadeEntite.selectionCascadeAvantPlan();
        actorEditableOptions = new ActorEditableOptions(new ArrayList<>());
    }

    public static void resetReglages(){
        //currMusic.valeurAzero();
        currLvl = "default.slf";
    }

    public static void upd(){
        

        if(Input.isMouseLeftReleased())  mousePressedinGUI = false;
        if(Input.isMouseLeftPressed() && isCursorInGUI())  mousePressedinGUItrue();

        entitySel.upd();
        actionfichierSel.upd();
        currBg.upd();
        currFg.upd();
        currMusic.upd();

        placement_rotate();
        key_layers();
        key_placement_libre();

        

        switch(entitySel.getOptionSelected()){
            case MODE_EDIT_ACTEUR           : {
                acteurSel.upd();         
                actorEditableOptions.upd();
            }break;
            case MODE_EDIT_TILEGRAPHIQUE    : tileGraphicSel.upd(); break;
            case MODE_EDIT_TILECOLLISION    : tileCollSel.upd();    break;
        }


        
    }

    public static void load(){
        TxtrStrg.loadFg((GUI.currFg.getOptionSelected()));
    }

    private static void print_key_pressed(){
        RayClass.rlj.core.IsKeyPressed(257);
        int key = RayClass.rlj.core.GetKeyPressed();
        if(key != 0){
            System.out.println(key);
        }
    }

    private static void key_layers(){
        if(RayClass.rlj.core.IsKeyPressed(69)){ //touche E
            if(currLayer == 1) currLayer = 2;
            else currLayer = 1;
        }

        if(RayClass.rlj.core.IsKeyPressed(82)){ //touche R
            if(drawOtherEntities == DRAW_OPAQUE) drawOtherEntities = DRAW_TRANSPARENT;
            else drawOtherEntities = DRAW_OPAQUE;
        }
    }

    private static void key_placement_libre(){
        if(RayClass.rlj.core.IsKeyPressed(84)){ //touche T
            placement_libre = !placement_libre;
        }
    }


    public static void draw(){        
        rShapes.DrawRectangleRec(bgRect, RayClass.rlj.textures.GetColor(0x404040FF));

        RayClass.rlj.text.DrawText("curr lvl : " + currLvl, (int)bgRect.width, 0, 20, Color.WHITE);

        RayClass.rlj.text.DrawText("curr layer : " + currLayer, 600, 0, 20, Color.WHITE);
        RayClass.rlj.text.DrawText("draw mode : " + drawOtherEntities, 750, 0, 20, Color.WHITE);
        RayClass.rlj.text.DrawText("placement_libre : " + placement_libre, 900, 0, 20, Color.WHITE);
        switch(entitySel.getOptionSelected()){
            case MODE_EDIT_ACTEUR           :
                acteurSel.draw();
                actorEditableOptions.draw();
                break;    

            case MODE_EDIT_TILEGRAPHIQUE    : tileGraphicSel.draw(); break;
            case MODE_EDIT_TILECOLLISION    : tileCollSel.draw();    break;
        }

        RayClass.rlj.text.DrawText("currRot " + currRot, 0, 220, 20, Color.WHITE);
        
        actionfichierSel.draw();
        currMusic.draw();
        currBg.draw();
        currFg.draw();

        entitySel.draw();
        
        
    }

    public static boolean isCursorInGUI(){
        return Input.getCursorPos().x < bgRect.width;
    }


    public static void mousePressedinGUItrue(){
        mousePressedinGUI = true;
    }

    public static boolean isMousePressedInGUI(){
        return mousePressedinGUI;
    }

    public static int getCurrRot(){
        return currRot;
    }

    
private static void placement_rotate(){
    //int keyPressed = RayClass.rlj.core.GetKeyPressed();
    //if(keyPressed != 0) System.out.println("keyPressed " + keyPressed);
    

    if(RayClass.rlj.core.IsKeyPressed(90)) {
        switch(currRot){
            case 0      : currRot = -90;    return;
            case -90    : currRot = 180;    return;
            case 180    : currRot = 90;     return;
            case 90     : currRot = 0;      return;
        }
    }

    if(RayClass.rlj.core.IsKeyPressed(88)){
        switch(currRot){
            case 0      : currRot = 90;     return;
            case -90    : currRot = 0;      return;
            case 180    : currRot = -90;    return;
            case 90     : currRot = 180;    return;
        }
    }


}


}
