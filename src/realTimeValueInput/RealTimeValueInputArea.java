package src.realTimeValueInput;

import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;

import src.Input;
import src.RayClass;

public class RealTimeValueInputArea {
    private RealTimeValueInputI value;
    private final String name;
    private int textSize;
    private int width;
    private int height;
    private int x;
    private int y;

    public RealTimeValueInputArea(String name, RealTimeValueInputI value){
        this.value = value; //peut etre ne pas mettre un RealTimeValueInputI en argument du constructeur ?
        this.name = name;
        this.textSize = 20;
        this.width = 300;
        this.height = 40;
        this.x = 800;
        this.y = 100;
    }

    public RealTimeValueInputArea(String name, int yPos, RealTimeValueInputI value){
        this.value = value; //peut etre ne pas mettre un RealTimeValueInputI en argument du constructeur ?
        this.name = name;
        this.textSize = 20;
        this.width = 300;
        this.height = 40;
        this.x = 0;
        this.y = yPos;
    }

    public String getInput(){
        return value.getInput();
    }

    private boolean isMouseClickedOnArea(){
        Rectangle area = new Rectangle(x, y, width, height);
        return Input.isMouseLeftPressed() && (RayClass.rlj.shapes.CheckCollisionPointRec(Input.getCursorPos(), area));
    }


    private boolean isMouseClickedOffArea(){
        Rectangle area = new Rectangle(x, y, width, height);
        return Input.isMouseLeftPressed() && !(RayClass.rlj.shapes.CheckCollisionPointRec(Input.getCursorPos(), area)) ;
    }

    
    public boolean upd(){
        if(isMouseClickedOnArea()){
            System.out.println("mouseClickedOnArea");
            value.setActive();
        }
        else if(value.isActive() && isMouseClickedOffArea() ){
            value.inputDone();
        }

        return value.upd();   
    }

    public void draw(){
        int bgColor = (value.isActive()) ? 0x707070FF : 0x303030FF;
        RayClass.rlj.shapes.DrawRectangle(x ,y, width, height, RayClass.rlj.textures.GetColor(bgColor));
        RayClass.rlj.shapes.DrawRectangleLines( x ,y, width, height, Color.LIGHTGRAY);
        RayClass.rlj.text.DrawText(name, x, y - height, textSize, Color.WHITE);
        RayClass.rlj.text.DrawText(value.getInput(), x, y, textSize, Color.WHITE);
    }


}
