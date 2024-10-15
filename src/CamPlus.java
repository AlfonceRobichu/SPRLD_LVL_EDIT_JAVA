package src;

import com.raylib.java.core.rCore;
import com.raylib.java.core.rcamera.Camera2D;
import com.raylib.java.raymath.Vector2;

import src.gui.GUI;

public class CamPlus {
    public static Camera2D cam = new Camera2D(new Vector2(Screen.centerWidth, Screen.centerHeight), new Vector2(), 0, 1) ;

    public static void camTarget_sub(Vector2 v){
        cam.target.x -= v.x;
        cam.target.y -= v.y;
    }

    public static void upd(){
        keyBoardMove();
        if(!GUI.isCursorInGUI()) zoomCtrl();
        
    }

    private static void keyBoardMove(){
        if(rCore.IsKeyDown(68)){    //d
            cam.target.x += 15;
        }

        if(rCore.IsKeyDown(65)){    //q
            cam.target.x -= 15;
        }
    
        if(rCore.IsKeyDown(87)){    //z
            cam.target.y -= 15;
        }

        if(rCore.IsKeyDown(83)){    //s
            cam.target.y += 15;
        }
    }

    private static void zoomCtrl(){
        if(Input.isMouseWheelPressed()) {cam.zoom = 1.0f; return;}

        float wheelMvmt = Input.GetMouseWheel();
        if(wheelMvmt < 0.0f){
            cam.zoom -= cam.zoom * 0.12;
        }
        else if(wheelMvmt > 0.0f){
            cam.zoom += cam.zoom * 0.12;
        }
    }

    public static void updOffsetFullscreenSwitch(){
        cam.offset.x = Screen.centerWidth;
        cam.offset.y = Screen.centerHeight;
    }

}
