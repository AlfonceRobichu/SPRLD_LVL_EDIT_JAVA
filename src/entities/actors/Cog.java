package src.entities.actors;

import java.util.List;

import com.raylib.java.raymath.Vector2;

import src.otherRealTimeValueInput.RealTimeFloat;
import src.otherRealTimeValueInput.RealTimeValue;

public class Cog extends Actor {
    public static final int ID = 1;
    public static final String name = "Cog";
    
    public RealTimeValue rotSpd;


    public Cog(int id, Vector2 pos, int rot){
        super(id, pos, rot, 128, 128);
        rotSpd = new RealTimeFloat("rotSpd", 0.2f);
    }


    public void displayInfo(){
        int offset = super.displayInfo(name);
        offset += optionsOffset;

        //rotSpd.draw();
    }


    @Override
    public List<RealTimeValue> getOptions() {
        return List.of(
            lookRight,
            rotSpd
        );
    }
}
