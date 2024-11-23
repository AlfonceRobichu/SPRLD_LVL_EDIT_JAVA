package src.entities.actors;

import java.util.List;

import com.raylib.java.raymath.Vector2;

import src.otherRealTimeValueInput.RealTimeValue;

public class Bumper extends Actor {
    public static final int ID = 3;
    public static final String name = "bumper";
    public Bumper(int id, Vector2 pos, int rot){
        super(id, pos, rot, 64, 64);
    }

    
    public void displayInfo(){
        super.displayInfo(name);
    }


    @Override
    public List<RealTimeValue> getOptions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOptions'");
    }
}
