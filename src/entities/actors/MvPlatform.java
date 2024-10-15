package src.entities.actors;

import com.raylib.java.raymath.Vector2;

import src.formes.Rect;

public class MvPlatform extends Actor {
    public static final int ID = 2;
    public static final String name = "mvPlatform";

    public static final String[] param_str = new String[1];

    public MvPlatform(int id, Vector2 pos, int rot){
        super(id, pos, rot);
        super.rect = Rect.setRect(pos, rot, 64, 64);
        param_str[0] = "moveSpd";
    }

    public void displayInfo(){
        super.displayInfo(name);
    }

}