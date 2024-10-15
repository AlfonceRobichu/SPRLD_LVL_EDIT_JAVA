package src;


import src.entities.TileGraphicLayer;
import src.gui.GUI;



public class Main {
    

    public static void main(String[] args) {
        RayClass.rlj.core.SetTargetFPS(60);
        
        new TxtrStrg();
        new GUI();
        new TileGraphicLayer();
        while (!RayClass.rlj.core.WindowShouldClose()){
            Screen.switchWidescreen();
            Logic.upd();
            Graphic.upd();
        }

        RayClass.rlj.core.CloseWindow();
    
    }
}
