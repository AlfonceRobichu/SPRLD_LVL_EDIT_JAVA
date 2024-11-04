package src.otherRealTimeValueInput;

import com.raylib.java.shapes.Rectangle;

import src.Input;
import src.RayClass;

public class RealTimeValueInputArea {
    private RealTimeValue realTimeValue;


    private final String name;
    private int textSize;
    private int width;
    private int height;
    private int x;
    private int y;
    
    public RealTimeValueInputArea(String name, RealTimeValue value){
        this.realTimeValue = value;
        this.name = name;
        this.textSize = 20;
        this.width = 300;
        this.height = 40;
        this.x = 800;
        this.y = 100;
    }

    private boolean isMouseClickedOnArea(){
        Rectangle area = new Rectangle(x, y, width, height);
        return Input.isMouseLeftPressed() && (RayClass.rlj.shapes.CheckCollisionPointRec(Input.getCursorPos(), area));
    }


    private boolean isMouseClickedOffArea(){
        Rectangle area = new Rectangle(x, y, width, height);
        return Input.isMouseLeftPressed() && !(RayClass.rlj.shapes.CheckCollisionPointRec(Input.getCursorPos(), area)) ;
    }

    
    public void upd(){
        if(isMouseClickedOnArea()){
            System.out.println("mouseClickedOnArea");
            RealTimeValueInput.getRealTimeValueInput().activateRealTimeValueInput(this);
        }
        else if(isMouseClickedOffArea() && RealTimeValueInput.getRealTimeValueInput().isActive()){
            RealTimeValueInput.getRealTimeValueInput().deactivateRealTimeValueInput();
        }
    }


    public boolean isCorrectlyFormed(String s){
        return realTimeValue.isCorrectlyFormed(s);
    }

    public void setValue(String s){
        realTimeValue.setValue(s);
    }


}
