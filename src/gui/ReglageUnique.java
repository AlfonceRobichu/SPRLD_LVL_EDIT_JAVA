package src.gui;

import com.raylib.java.core.Color;

import src.RayClass;

public class ReglageUnique {
    protected static int textSize = 30;
    protected static int width = 300;
    protected static int height = 40;
    protected static int x = 0;
    protected int y;

    private String nom;
    public int valeur;

    public ReglageUnique(String nom, int y){
        this.nom = nom;
        this.y = y;
        valeur = 0;
    }

    public void valeurAzero(){
        valeur = 0;
    }

    public void draw(){
        RayClass.rlj.shapes.DrawRectangle(x ,y, width, height, RayClass.rlj.textures.GetColor(0x303030FF));
        RayClass.rlj.shapes.DrawRectangleLines( x ,y, width, height, Color.LIGHTGRAY);
        RayClass.rlj.text.DrawText(nom, x, y, textSize, Color.WHITE);
        RayClass.rlj.text.DrawText(""+valeur, x + width - 30, y, textSize, Color.WHITE);
    }


}
