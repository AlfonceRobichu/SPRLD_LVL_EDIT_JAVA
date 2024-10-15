package src.gui;

import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;

import src.Input;
import src.RayClass;
import src.Scene;
import src.entities.LvlEle;
import src.gestionFichier.FichierIO;

public class SelectionCascadeFichier extends SelectionCascade{

    public SelectionCascadeFichier(){
        super("Fichier", 0, 0);
        String [] options = new String[] {"nouveau fichier", "ouvrir", "enregistrer", "enregistrer sous"};
        super.initOptions(options);
    }

    public void upd(){

    
        if(debale) GUI.mousePressedinGUItrue();
        if(!Input.isMouseLeftPressed()) return;
        mouseEvent();
    }


    private void mouseEvent(){
        Rectangle selRect = new Rectangle(x, y, width, height);
        if(RayClass.rlj.shapes.CheckCollisionPointRec(Input.getCursorPos(), selRect)){  //clique sur l'en-tete de la cascade
            debale = !debale;
            GUI.mousePressedinGUItrue();
            return;
        }
        if(!debale) return;

        debale = false;

        selRect.x += width;
        int i = 0;
        for(i = 0; i < options.length; i++){
            if(RayClass.rlj.shapes.CheckCollisionPointRec(Input.getCursorPos(), selRect)){
                break;
            }
            selRect.y += height;
        }

        switch(i){
            case 0 :    //nouveau fichier
                LvlEle.empty();
                GUI.resetReglages();
            break;         
            case 1 : {              //ouvir
                Scene.changeScene(Scene.FILELOAD);
            }break;         
            case 2 : 
                FichierIO.saveLvlFile(GUI.currLvl);
            break;         //enregistrer
            case 3 : 
                Scene.changeScene(Scene.FILESAVE);
            break;         //enregistrer sous
            default : return;
        }
    }

    public void draw(){
        RayClass.rlj.shapes.DrawRectangle(x ,y, width, height, RayClass.rlj.textures.GetColor(0x303030FF));
        RayClass.rlj.shapes.DrawRectangleLines( x ,y, width, height, Color.LIGHTGRAY);
        RayClass.rlj.text.DrawText(nom, x, y, textSize, Color.WHITE);
        if(!debale) return;
        
        int yOptioni = y;
        int xOptions = x + width;
        for(int i = 0; i < options.length; i++){
            RayClass.rlj.shapes.DrawRectangle(xOptions , yOptioni, width, height, RayClass.rlj.textures.GetColor(0x303030FF));
            RayClass.rlj.shapes.DrawRectangleLines( xOptions , yOptioni, width, height, Color.LIGHTGRAY);
            RayClass.rlj.text.DrawText(options[i], xOptions, yOptioni, textSize, Color.WHITE);
            yOptioni += height;
        }
    }

}
