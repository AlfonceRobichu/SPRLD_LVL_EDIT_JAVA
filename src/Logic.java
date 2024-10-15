package src;

import src.entities.LvlEle;
import src.gestionFichier.GestionFichierChargement;
import src.gestionFichier.GestionFichierEnregistrement;
import src.gui.GUI;

public class Logic {

    public static void upd(){
        
        Input.upd();
        switch(Scene.currState){
            case Scene.LOAD   : load();   break;
            case Scene.PLAY   : play();   break;
            case Scene.UNLOAD : unload(); break;
        }
    }
       


    private static void load(){
        Scene.currScn = Scene.nextScn;
        switch(Scene.currScn){
            case Scene.EDIT : GUI.load();
            break;
            case Scene.FILELOAD : GestionFichierChargement.load();      break;
            case Scene.FILESAVE : GestionFichierEnregistrement.load();  break;
        }
        Scene.currState = Scene.PLAY;
    }    




    private static void play(){
        switch(Scene.currScn){
            case Scene.EDIT : {
                CamPlus.upd();
                GUI.upd();
                LvlEle.upd();
            }break;

            case Scene.FILELOAD : {
                GestionFichierChargement.upd();
                break;
            }

            case Scene.FILESAVE : {
                GestionFichierEnregistrement.upd();
                break;
            }
        }
    }

    private static void unload(){
        Scene.currState = Scene.LOAD;
    }


}
