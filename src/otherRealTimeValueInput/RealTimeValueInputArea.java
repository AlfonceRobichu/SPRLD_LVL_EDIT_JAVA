package src.otherRealTimeValueInput;

import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;

import src.Input;
import src.RayClass;

public class RealTimeValueInputArea extends EditableValueArea{
    private RealTimeValue realTimeValue;
    
    
    public RealTimeValueInputArea(int xPos, int yPos, RealTimeValue value){
        super(value.getName(), xPos, yPos);
        this.realTimeValue = value;
    }

    private boolean isMouseClickedOnArea(){
        Rectangle area = new Rectangle(xPos, yPos, width, height);
        return Input.isMouseLeftPressed() && (RayClass.rlj.shapes.CheckCollisionPointRec(Input.getCursorPos(), area));
    }


    private boolean isMouseClickedOffArea(){
        Rectangle area = new Rectangle(xPos, yPos, width, height);
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

    public void draw(){
        RayClass.rlj.shapes.DrawRectangle(xPos , yPos, width, height, RayClass.rlj.textures.GetColor(0x303030FF));
        RayClass.rlj.shapes.DrawRectangleLines( xPos ,yPos, width, height, Color.LIGHTGRAY);
        RayClass.rlj.text.DrawText(name, xPos, yPos, textSize, Color.WHITE);
        RayClass.rlj.text.DrawText(""+realTimeValue.toString(), xPos + width - 30, yPos, textSize, Color.WHITE);
    }


    public boolean isCorrectlyFormed(String s){
        return realTimeValue.isCorrectlyFormed(s);
    }

    public void setValue(String s){
        realTimeValue.setValue(s);
    }

}
