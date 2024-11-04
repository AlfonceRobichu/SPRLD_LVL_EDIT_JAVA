package src.entities.actors;

import com.raylib.java.raymath.Vector2;

import src.formes.Rect;
import src.otherRealTimeValueInput.RealTimeFloat;
import src.otherRealTimeValueInput.RealTimeValue;

public class Cog extends Actor {
    public static final int ID = 1;
    public static final String name = "cog";
    
    public RealTimeValue rotSpd;
    //public float rotSpd = 0.2f;


    public Cog(int id, Vector2 pos, int rot){
        super(id, pos, rot);
        super.rect = Rect.setRect(pos, rot, 128, 128);
        //rotSpd = new RealTimeValueInputArea("RotSpd", super.displayInfo(name), new StringRealTimeInput("0.0f"));
        rotSpd = new RealTimeFloat(0.2f);
    }

    @Override
    public void updInfo(){
        rotSpd.upd();
        //Rectangle selRect = new Rectangle(x, y, width, height);
    }


    public void displayInfo(){
        int offset = super.displayInfo(name);
        offset += optionsOffset;

        //rotSpd.draw();
    }
}
