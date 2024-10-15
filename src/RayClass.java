package src;

import com.raylib.java.Raylib;
import com.raylib.java.raymath.Vector2;

public class RayClass {
    public static Raylib rlj = new Raylib(Screen.width, Screen.height, "sprld_lvl_editor");

    public static float Vector2DotProduct(Vector2 v1, Vector2 v2){
        return (v1.x * v2.x) + (v1.y * v2.y);
    }

}
