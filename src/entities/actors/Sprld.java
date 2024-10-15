package src.entities.actors;

import com.raylib.java.raymath.Vector2;

import src.formes.Rect;

public class Sprld extends Actor {
    public static final int ID = 0;
    public static final String name = "springleedee";
    public Sprld(int id, Vector2 pos, int rot){
        super(id, pos, rot);
        super.rect = Rect.setRect(pos, rot, 32, 32);
    }


    public void displayInfo(){
        super.displayInfo(name);
    }

}

