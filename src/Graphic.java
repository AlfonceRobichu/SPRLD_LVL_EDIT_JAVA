package src;

import com.raylib.java.core.Color;

import src.entities.LvlEle;
import src.gestionFichier.GestionFichierChargement;
import src.gestionFichier.GestionFichierEnregistrement;
import src.gui.GUI;

public class Graphic {
    public static void upd(){
        switch(Scene.currScn){
            case Scene.EDIT     : drawEdit(); return;
            case Scene.FILELOAD : GestionFichierChargement.draw(); return;
            case Scene.FILESAVE : GestionFichierEnregistrement.draw(); return;
        }
    }

    private static int moveWithCamX(int pos){
        return pos + (int)CamPlus.cam.target.x;
    }

    private static int moveWithCamY(int pos){
        return pos + (int)CamPlus.cam.target.y;
    }


    private static void drawEdit(){
        RayClass.rlj.core.BeginDrawing();
        RayClass.rlj.core.ClearBackground(Color.DARKGRAY);
        RayClass.rlj.core.BeginMode2D(CamPlus.cam);

        RayClass.rlj.shapes.DrawLine(moveWithCamX(-1366), 0, moveWithCamX(1366), 0, Color.RED);
        RayClass.rlj.shapes.DrawLine(0, moveWithCamY(-1366), 0, moveWithCamY(1366), Color.GREEN);

        LvlEle.draw();

        Input.draw();
        

        RayClass.rlj.core.EndMode2D();

        GUI.draw();

        Input.drawCursorPosText();

        RayClass.rlj.text.DrawFPS(Screen.width-80, 5, Color.WHITE);
        
        RayClass.rlj.core.EndDrawing();


    }

}
