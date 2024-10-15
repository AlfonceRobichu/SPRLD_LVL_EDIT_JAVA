package src.selectionTile;


public abstract class SelectionTile {

    protected int x;
    protected int y;
    protected int nbcolumns;

    protected int currOptionSelected;

    protected static int txtrDisplaySize = 64;

    
    public SelectionTile(int y, int nbcolumns){
        x = 0;
        this.y = y;
        this.nbcolumns = nbcolumns;
        currOptionSelected = 0;
    }

    public int getCurrOptionSelected(){
        return currOptionSelected;
    }

  

}
