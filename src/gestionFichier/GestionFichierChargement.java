package src.gestionFichier;

import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;

import src.Input;
import src.RayClass;
import src.Scene;

public class GestionFichierChargement {
    private static String[] lvlFiles;

    private static int x = 0;
    private static int y = 100;
    private static int width = 300;
    private static int height = 60;
    private static int decalageEntreOptions = 80;
    private static int textSize = 40;
    

    public static void load(){
        System.out.println("chargement des fichiers");
        lvlFiles = RayClass.rlj.core.GetDirectoryFiles(FichierIO.getLvlFilePath());
    }


    public static void upd(){
        if(Input.isMouseRightPressed()){
            Scene.changeScene(Scene.EDIT);
            return;
        }

        if(!Input.isMouseLeftPressed()) return;

        Rectangle selRect = new Rectangle(x, y, width, height);
    
        for(int i = 0; i < lvlFiles.length; i++){
            if(RayClass.rlj.shapes.CheckCollisionPointRec(Input.getCursorPos(), selRect)){
                Scene.changeScene(Scene.EDIT);
                FichierIO.loadLvlFile(lvlFiles[i]);
                return;
            }
            selRect.y += decalageEntreOptions;    
        }
    }


    public static void draw(){
        RayClass.rlj.core.BeginDrawing();
        RayClass.rlj.core.ClearBackground(Color.DARKGRAY);

        RayClass.rlj.text.DrawText("ouvrir un fichier", 0, 0, 50, Color.WHITE);
        RayClass.rlj.shapes.DrawRectangle(x ,y, width, height, RayClass.rlj.textures.GetColor(0x303030FF));
        RayClass.rlj.shapes.DrawRectangleLines( x ,y, width, height, Color.LIGHTGRAY);

        int yOptioni = y;
        for(int i = 0; i < lvlFiles.length; i++){
            RayClass.rlj.shapes.DrawRectangle(x , yOptioni, width, height, RayClass.rlj.textures.GetColor(0x303030FF));
            RayClass.rlj.shapes.DrawRectangleLines( x , yOptioni, width, height, Color.LIGHTGRAY);
            RayClass.rlj.text.DrawText(lvlFiles[i], x, yOptioni, textSize, Color.WHITE);

            yOptioni += decalageEntreOptions;

        }

        
        RayClass.rlj.core.EndDrawing();
    }

}