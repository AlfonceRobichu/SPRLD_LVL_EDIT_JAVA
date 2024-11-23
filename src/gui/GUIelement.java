package src.gui;


public abstract class GUIelement{
    protected static int height = 30;
    protected static int width = 200;
    protected static int textSize = 20;
    protected int xPos;
    protected int yPos;
    protected String name;


    protected GUIelement(String name, int xPos, int yPos){
        this.name = name;
        this.xPos = xPos;
        this.yPos = yPos;
    }


    public abstract void upd();

    public abstract void draw();
}
