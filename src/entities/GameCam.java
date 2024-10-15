package src.entities;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;

import src.formes.Rect;

public class GameCam {
    public Rect rect;

    public int mode;

    public static final int 
    MODE_STATIC     = 0,
    MODE_STANDARD   = 1;
                    
    

    public GameCam(){
        rect = new Rect(new Vector2(), 64, 64);
        mode = MODE_STATIC;
    }


    public GameCam(Vector2 pos, int mode){
        rect = new Rect(pos, 64, 64);
        this.mode = mode;
    }


    public Vector2 getPos(){
        return rect.pos;
    }

    public void draw(){
        rect.drawSides(Color.PURPLE); 
    }

    public String toString(){
        return (int)rect.pos.x + " " + (int)rect.pos.y + " " + mode + "\n";
    }

}
