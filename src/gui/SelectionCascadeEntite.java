package src.gui;

import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;

import src.Input;
import src.RayClass;
import src.entities.actors.Actor;

public class SelectionCascadeEntite extends GUIelement {
    private int currOptionSelected;

    private int nbOptionsSimultanees;
    private int premiereOptionCour;

    private boolean debale; 
    protected SelectionCascadeElt options[];

    public SelectionCascadeEntite(String name, String options[], int yPos, int sel_start_y, int nbOptionsSimultanees){
       // super(nom, options, yPos, sel_start_y, nbOptionsSimultanees);
        super(name, 0, yPos);
        this.nbOptionsSimultanees = nbOptionsSimultanees;
        currOptionSelected = 0;
        this.premiereOptionCour = 0;
        debale = false;

        int nbOptions = options.length;
        this.options = new SelectionCascadeElt[nbOptions];
        for(int i = 0; i < nbOptions; i++)
            this.options[i] =  new SelectionCascadeElt(options[i]);

    }

    
    public static SelectionCascadeEntite selectionCascadeArrierePlan(){
        String selOptions[] = {             
            "Section4_0",
            "Section4_1",
            "Section0_0",
            "Section1_0",
            "Section2_0",
            "Section3_0",
            "Section5_0",
        }; 
        return new SelectionCascadeEntite("Arriere plan", selOptions, 60, 60, 10);
    }

    public static SelectionCascadeEntite selectionCascadeAvantPlan(){
        String selOptions[] = {
            "Section0_0",
            "Section1_0",
            "Section2_0",
            "Section3_0",
            "Section4_0",
            "Section4_1",
            "Section5_0",
        }; 
        return new SelectionCascadeEntite("Avant plan", selOptions, 120, 120, 10);
    }

    public static SelectionCascadeEntite selectionCascadeMusique(){
        String selOptions[] = {
            "Section0_0", 
            "Section0_1",
            "Section0_2",
            "Section1_0",
            "Section1_1",
            "Section1_2",
            "Section2_0",
            "Section2_1",
            "Section2_2",
            "Section3_0",
            "Section3_1",
            "Section3_2",
            "Section4_0",
            "Section4_1",
            "Section4_2",
            "Section4_3",
            "Section5_0",
            "Section5_1",
            "Section5_2",
            "Section0_Boss0",
            "Section1_Boss0",
            "Section2_Boss0",
            "Section3_Boss0",
            "Section4_Boss0",
            "Section5_Boss0",
            "Section5_Boss1",
        }; 
        return new SelectionCascadeEntite("Musique", selOptions, 180, 180, 10);
    }


    public static SelectionCascadeEntite selectionCascadeEntites(){
        String entitySelOptions[] = {"Acteurs", "Tiles graphiques", "tiles collision"}; 
        return new SelectionCascadeEntite("Entite", entitySelOptions, 360, 300, 3);
    }
    public static SelectionCascadeEntite selectionCascadeActeurs(){
        return new SelectionCascadeEntite("Acteur", Actor.acteurSelOptions, 430, 300, 10);
    }

    

    


    public int getOptionSelected(){
        return currOptionSelected;
    }

    public void setOptionSelected(int i){
        currOptionSelected = i;
    }

    private boolean isCursorInSelectionCascade(){
        Rectangle rectRecouvrant = new Rectangle(xPos+width, yPos, width, height);
        if(debale) rectRecouvrant.height *= nbOptionsSimultanees;
        return(RayClass.rlj.shapes.CheckCollisionPointRec(Input.getCursorPos(), rectRecouvrant)) ;
    }

    public void upd(){
        /* 
        if(!isCursorInSelectionCascade()){
            if(Input.isMouseLeftPressed()) {debale = false;} //GUI.mousePressedinGUItrue();}
            return;
        }*/
        if(debale) GUI.mousePressedinGUItrue(); 
        if(isCursorInSelectionCascade()){
            if     (Input.GetMouseWheel() > 0.0f && premiereOptionCour > 0) premiereOptionCour--;
            else if(Input.GetMouseWheel() < 0.0f && (premiereOptionCour+nbOptionsSimultanees) < options.length) premiereOptionCour++;
        }

        if(!Input.isMouseLeftPressed()) return;
        mouseEvent();
    }

    private void mouseEvent(){
        Rectangle selRect = new Rectangle(xPos, yPos, width, height);
        if(RayClass.rlj.shapes.CheckCollisionPointRec(Input.getCursorPos(), selRect)){  //clique sur l'en-tete de la cascade
            debale = !debale;
            premiereOptionCour = 0;
            GUI.mousePressedinGUItrue();
            return;
        }
        
        if(!debale) return;
        
        debale = false;
        int xOption = xPos + width;
        int yOption = yPos;
        
        for(int i = premiereOptionCour; i < options.length && (i-premiereOptionCour) < nbOptionsSimultanees; i++){
            if(options[i].mouseEvent(xOption, yOption, width, height)){
                debale = false;
                premiereOptionCour = 0;
                currOptionSelected = i;
                return;
            }
            yOption += height;
        }
    }

    

    public void draw(){
        RayClass.rlj.text.DrawText(name, xPos, yPos-textSize, textSize, Color.WHITE);
        options[currOptionSelected].draw(xPos, yPos, width, height, textSize);
        if(!debale) return;
        
        int yOptioni = yPos;
        int xOptions = xPos + width;
        for(int i = premiereOptionCour; i < options.length && (i-premiereOptionCour) < nbOptionsSimultanees; i++){
            options[i].draw(xOptions, yOptioni, width, height, textSize);
            yOptioni += height;
        }
        if(premiereOptionCour+nbOptionsSimultanees < options.length){
            RayClass.rlj.text.DrawText("\\/", xOptions + width, yOptioni, textSize, Color.WHITE);
        }
        if(premiereOptionCour > 0){
            RayClass.rlj.text.DrawText("/\\", xOptions + width, yPos, textSize, Color.WHITE);
        }

    }


}
