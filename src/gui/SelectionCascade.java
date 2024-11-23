package src.gui;


import com.raylib.java.shapes.Rectangle;


import src.Input;
import src.RayClass;

public abstract class SelectionCascade extends GUIelement {
    protected int sel_start_y;

    protected SelectionCascadeElt options[];
    protected boolean debale;

    protected int nbOptionsSimultanees; //nb d'options simultanément visibles
    protected int premiereOptionCour = 0;

    

    public SelectionCascade(String name, String options[], int y, int sel_start_y, int nbOptionsSimultanees){
        super(name, 0, y);
        this.sel_start_y = sel_start_y;
        debale = false;
        
        this.nbOptionsSimultanees = nbOptionsSimultanees;

        int nbOptions = options.length;
        this.options = new SelectionCascadeElt[nbOptions];
        for(int i = 0; i < nbOptions; i++)
            this.options[i] =  new SelectionCascadeElt(options[i]);
    }


    protected SelectionCascade(String name, int y, int sel_start_y){
        super(name, 0, y);
        this.sel_start_y = sel_start_y;
        debale = false;
        this.options = null;
        this.name = name;
    }

    protected void initOptions(String options[]){
        int nbOptions = options.length;
        this.options = new SelectionCascadeElt[nbOptions];
        for(int i = 0; i < nbOptions; i++)
            this.options[i] = new SelectionCascadeElt(options[i]);
    }


    public String getOption(int index){
        return options[index].getValue();
    }
    
    

    protected boolean isCursorInSelectionCascade(){
        Rectangle rectRecouvrant = new Rectangle(xPos+width, yPos, width, height);
        if(debale) rectRecouvrant.height *= nbOptionsSimultanees;
        return(RayClass.rlj.shapes.CheckCollisionPointRec(Input.getCursorPos(), rectRecouvrant)) ;
    }



}
