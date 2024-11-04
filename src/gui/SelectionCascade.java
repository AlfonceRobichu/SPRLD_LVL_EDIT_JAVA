package src.gui;


import com.raylib.java.shapes.Rectangle;

import src.Input;
import src.RayClass;

public abstract class SelectionCascade extends GUIelement {
    protected static int textSize = 20;
    protected static int width = 300;
    protected static int height = 30;
    protected static int x = 0;
    protected int y;
    protected int sel_start_y;

    protected String options[];
    protected boolean debale;

    protected int nbOptionsSimultanees; //nb d'options simultanément visibles
    protected int premiereOptionCour = 0;

    protected String nom;

    public SelectionCascade(String nom, String options[], int y, int sel_start_y, int nbOptionsSimultanees){
        this.y = y;
        this.sel_start_y = sel_start_y;
        debale = false;
        this.nom = nom;
        this.nbOptionsSimultanees = nbOptionsSimultanees;

        int nbOptions = options.length;
        this.options = new String[nbOptions];
        for(int i = 0; i < nbOptions; i++)
            this.options[i] = options[i];
    }


    protected SelectionCascade(String nom, int y, int sel_start_y){
        this.y = y;
        this.sel_start_y = sel_start_y;
        debale = false;
        this.options = null;
        this.nom = nom;
    }

    protected void initOptions(String options[]){
        int nbOptions = options.length;
        this.options = new String[nbOptions];
        for(int i = 0; i < nbOptions; i++)
            this.options[i] = options[i];
    }


    public String getOption(int index){
        return options[index];
    }
    
    

    protected boolean isCursorInSelectionCascade(){
        Rectangle rectRecouvrant = new Rectangle(x+width, y, width, height);
        if(debale) rectRecouvrant.height *= nbOptionsSimultanees;
        return(RayClass.rlj.shapes.CheckCollisionPointRec(Input.getCursorPos(), rectRecouvrant)) ;
    }



}
