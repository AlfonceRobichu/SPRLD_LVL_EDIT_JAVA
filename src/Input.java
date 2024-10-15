package src;
import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;

import src.gui.GUI;

public class Input {
    public static int tailleCaseX = 64;
    public static int tailleCaseY = 64;

    private static Vector2 cursorPos = new Vector2(0, 0);
    private static Vector2 cursorPrevPos = new Vector2(0, 0);
    private static Vector2 cursorPosCam = new Vector2(0, 0);
    private static Vector2 cursorDelta = new Vector2(0, 0);
    private static Vector2 cursorDeltaCam = new Vector2(0, 0);
    private static Vector2 cursorDeltaCase = new Vector2(0, 0);
    private static Vector2 cursorPosCase = new Vector2(0, 0);
    private static boolean mouseLeftPressed = false;
    private static boolean mouseLeftDown = false;
    private static boolean mouseLeftReleased = false;
    private static boolean mouseLeftUp = false;
    private static boolean mouseRightPressed = false;
    private static boolean mouseRightDown = false;
    private static boolean mouseRightReleased = false;
    private static boolean mouseRightUp = false;
    private static float mouseWheel = 0.0f;
    private static boolean mouseWheelPressed = false;
    private static int keyPressed = 0;
    
    public Input(){
        cursorPrevPos = new Vector2(0, 0);
        cursorPos = new Vector2(0, 0);
        cursorPosCam = new Vector2(0, 0);
        cursorDelta = new Vector2(0, 0);
        cursorDeltaCam = new Vector2(0, 0);
        cursorDeltaCase = new Vector2(0, 0);
        mouseLeftPressed = false;
        mouseLeftDown = false;
        mouseLeftReleased = false;
        mouseLeftUp = false;
        mouseRightPressed = false;
        mouseRightDown = false;
        mouseRightReleased = false;
        mouseRightUp = false;
        mouseWheel = 0.0f;
        mouseWheelPressed = false;
    }

    public static void upd(){
        cursorPrevPos.x = cursorPos.x;
        cursorPrevPos.y = cursorPos.y;

        cursorPos =  rCore.GetMousePosition();
    
        cursorDelta.x = cursorPos.x - cursorPrevPos.x;
        cursorDelta.y = cursorPos.y - cursorPrevPos.y;

        cursorDeltaCam.x = cursorPosCam.x;
        cursorDeltaCam.y = cursorPosCam.y;                      //maj 1 delta de la pos du curseur par camera

        cursorPosCam.x = cursorPos.x / CamPlus.cam.zoom;
        cursorPosCam.y = cursorPos.y / CamPlus.cam.zoom;
        cursorPosCam.x -= (CamPlus.cam.offset.x/CamPlus.cam.zoom) - CamPlus.cam.target.x;
        cursorPosCam.y -= (CamPlus.cam.offset.y/CamPlus.cam.zoom) - CamPlus.cam.target.y;
        
    
        cursorDeltaCam.x = cursorPosCam.x - cursorDeltaCam.x;   //maj 2 delta de la pos du curseur par camera
        cursorDeltaCam.y = cursorPosCam.y - cursorDeltaCam.y;

        cursorDeltaCase.x = cursorPosCase.x;                    //maj 1 delta de la pos du curseur sur case par camera
        cursorDeltaCase.y = cursorPosCase.y;

        int cursorCaseX = (int) (cursorPosCam.x/tailleCaseX)*tailleCaseX;
        int cursorCaseY = (int) (cursorPosCam.y/tailleCaseY)*tailleCaseY;
        cursorCaseX -= (cursorPosCam.x < 0.0f)? tailleCaseX : 0;
        cursorCaseY -= (cursorPosCam.y < 0.0f)? tailleCaseY : 0;
        cursorPosCase.x = cursorCaseX + (tailleCaseX>>1);
        cursorPosCase.y = cursorCaseY + (tailleCaseY>>1);

        cursorDeltaCase.x = cursorPosCase.x - cursorDeltaCase.x;    //maj 2 delta de la pos du curseur sur case par camera
        cursorDeltaCase.y = cursorPosCase.y - cursorDeltaCase.y;


        mouseLeftPressed = RayClass.rlj.core.IsMouseButtonPressed(0);
        mouseLeftDown = rCore.IsMouseButtonDown(0);
        mouseLeftUp = RayClass.rlj.core.IsMouseButtonUp(0);
        mouseLeftReleased = RayClass.rlj.core.IsMouseButtonReleased(0);

        mouseRightPressed = RayClass.rlj.core.IsMouseButtonPressed(1);
        mouseRightDown = rCore.IsMouseButtonDown(1);
        mouseRightUp = RayClass.rlj.core.IsMouseButtonUp(1);
        mouseRightReleased = RayClass.rlj.core.IsMouseButtonReleased(1);
        mouseWheelPressed = RayClass.rlj.core.IsMouseButtonPressed(2);
        mouseWheel = rCore.GetMouseWheelMove();

    }


    public static Vector2 getCursorPos(){
        return cursorPos;
    }

    public static Vector2 getCursorPosCam(){
        return cursorPosCam;
    }

    public static Vector2 getCursorDelta(){
        return cursorDelta;
    }

    public static Vector2 getCursorPosCase(){
        return cursorPosCase;
    }

    public static boolean isMouseLeftPressed(){
        return mouseLeftPressed;
    }

    public static boolean isMouseLeftDown(){
        return mouseLeftDown;
    }

    public static float GetMouseWheel(){
        return mouseWheel;
    }

    public static boolean isMouseRightDown(){
        return mouseRightDown;
    }

    public static boolean isMouseLeftReleased(){
        return mouseLeftReleased;
    }

    public static boolean isMouseRightPressed(){
        return mouseRightPressed;
    }


    public static boolean isMouseWheelPressed(){
        return mouseWheelPressed;
    }

    public static Vector2 getCursorDeltaCam(){
        return cursorDeltaCam;
    }

    public static Vector2 getFreeOrCaseCursorPosCam(){
        if (GUI.placement_libre == true) return cursorPosCam;
        return cursorPosCase;
    }

    public static Vector2 getFreeOrCaseCursorDeltaCam(){
        if (GUI.placement_libre == true) return cursorDeltaCam;
        return cursorDeltaCase;
    }

/*     public static void setTailleCase(int x, int y){
        tailleCaseX = x;
        tailleCaseY = y;
    }*/

    public static void draw(){
        //on dessine le carre de selection
        Rectangle rect = new Rectangle(cursorPosCase.x, cursorPosCase.y, tailleCaseX, tailleCaseY);
        Vector2 origin = new Vector2(tailleCaseX>>1, tailleCaseY>>1);
        rShapes.DrawRectanglePro(rect, origin, 0.0f, new Color(255, 255, 255, 100));
    }

    private static int cursorPosTextSize = 20;
    private static int cursorPosTextX = 300;
    private static int cursorPosTextY = Screen.height - (cursorPosTextSize<<2);
    

    public static void drawCursorPosText(){

        RayClass.rlj.text.DrawText("x:"+cursorPosCam.x + "\ny:" + cursorPosCam.y, cursorPosTextX, cursorPosTextY, cursorPosTextSize, Color.WHITE);
        //RayClass.rlj.text.DrawText("y:"+, cursorPosTextX, cursorPosTextY, cursorPosTextSize, Color.WHITE);

    }

}
