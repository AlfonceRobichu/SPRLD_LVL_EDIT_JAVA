package src.entities.actors;

import com.raylib.java.raymath.Vector2;

import src.formes.Rect;

public class Bumper extends Actor {
    public static final int ID = 3;
    public static final String name = "bumper";
    public Bumper(int id, Vector2 pos, int rot){
        super(id, pos, rot);
        super.rect = Rect.setRect(pos, rot, 64, 64);
    }

    
    public void displayInfo(){
        super.displayInfo(name);
    }
}
