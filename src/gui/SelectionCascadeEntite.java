package src.gui;

import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;

import src.Input;
import src.RayClass;
import src.entities.actors.Actor;

public class SelectionCascadeEntite extends SelectionCascade {
    protected int currOptionSelected;

    //private int nbOptionsSimultanees;
    //private int premiereOptionCour = 0;

    public SelectionCascadeEntite(String nom, String options[], int y, int sel_start_y, int nbOptionsSimultanees){
        super(nom, options, y, sel_start_y, nbOptionsSimultanees);
        currOptionSelected = 0;
    }

    
    public static SelectionCascadeEntite selectionCascadeArrierePlan(){
        String selOptions[] = {             
            "Section4_0",
            "Section4_1",
            "Section0_0"
        }; 
        return new SelectionCascadeEntite("Arriere plan", selOptions, 60, 60, 3);
    }

    public static SelectionCascadeEntite selectionCascadeAvantPlan(){
        String selOptions[] = {
            "Section0_0",
            "Section4_0"
        }; 
        return new SelectionCascadeEntite("Avant plan", selOptions, 120, 120, 2);
    }

    public static SelectionCascadeEntite selectionCascadeMusique(){
        String selOptions[] = {
            "Section0_0", 
            "Section0_1",
            "Section0_2"
        }; 
        return new SelectionCascadeEntite("Musique", selOptions, 180, 180, 3);
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
        Rectangle selRect = new Rectangle(x, y, width, height);
        if(RayClass.rlj.shapes.CheckCollisionPointRec(Input.getCursorPos(), selRect)){  //clique sur l'en-tete de la cascade
            debale = !debale;
            premiereOptionCour = 0;
            GUI.mousePressedinGUItrue();
            return;
        }
        
        if(!debale) return;
        
        debale = false;
        selRect.y = sel_start_y;
        selRect.x += width;
        for(int i = premiereOptionCour; i < options.length && (i-premiereOptionCour) < nbOptionsSimultanees; i++){
            if(RayClass.rlj.shapes.CheckCollisionPointRec(Input.getCursorPos(), selRect)){
                debale = false;
                premiereOptionCour = 0;
                currOptionSelected = i;
                return;
            }
            selRect.y += height;
        }
    }

    

    public void draw(){
        RayClass.rlj.text.DrawText(nom, x, y-textSize, textSize, Color.WHITE);
        RayClass.rlj.shapes.DrawRectangle(x ,y, width, height, RayClass.rlj.textures.GetColor(0x303030FF));
        RayClass.rlj.shapes.DrawRectangleLines( x ,y, width, height, Color.LIGHTGRAY);
        RayClass.rlj.text.DrawText(options[currOptionSelected], x, y, textSize, Color.WHITE);
        if(!debale) return;
        
        int yOptioni = sel_start_y;
        int xOptions = x + width;
        for(int i = premiereOptionCour; i < options.length && (i-premiereOptionCour) < nbOptionsSimultanees; i++){
            
            RayClass.rlj.shapes.DrawRectangle(xOptions , yOptioni, width, height, RayClass.rlj.textures.GetColor(0x303030FF));
            RayClass.rlj.shapes.DrawRectangleLines( xOptions , yOptioni, width, height, Color.LIGHTGRAY);
            RayClass.rlj.text.DrawText(options[i], xOptions, yOptioni, textSize, Color.WHITE);
            yOptioni += height;
        }
        if(premiereOptionCour+nbOptionsSimultanees < options.length){
            RayClass.rlj.text.DrawText("\\/", xOptions + width, yOptioni, textSize, Color.WHITE);
        }
        if(premiereOptionCour > 0){
            RayClass.rlj.text.DrawText("/\\", xOptions + width, y, textSize, Color.WHITE);
        }

    }

}
