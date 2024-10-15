package src.formes;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;

import src.RayClass;
import src.gui.GUI;

public class Rect {
    public Vector2 pos;
    public int width;
    public int height;

    public Rect(Vector2 pos, int width, int height){
        this.pos = new Vector2(pos.x, pos.y);
        this.width = width;
        this.height = height;
    }

    public Vector2 getTLcorner(){
        return new Vector2(pos.x - (width), pos.y - (height));
    }
    
    private Vector2 getTRcorner(){
        return new Vector2(pos.x + (width), pos.y - (height));
    }
    
    private Vector2 getBLcorner(){
        return new Vector2(pos.x - (width), pos.y + height);
    }
    
    private Vector2 getBRcorner(){
        return new Vector2(pos.x + (width), pos.y + (height));
    }

    public void getAllCorners( Vector2 tab[]){
        tab[0] = getTLcorner();
        tab[1] = getTRcorner();
        tab[2] = getBRcorner();
        tab[3] = getBLcorner();
    }
    
    ///return true si vect est dans rect
    public boolean isVectorInRect(Vector2 vect){
        if(vect.x <= pos.x - width || vect.x >= pos.x + width) return false;
        return (vect.y > pos.y - height && vect.y < pos.y + height);
    }
    /* 
    public boolean isCollidingWithOtherRect(Rect other){
        
        if ((int)(other.pos.x) + other.width + 1 <= (int)(pos.x) - width - 1 || (int)(other.pos.x) - other.width - 1 >= (int)(pos.x) + width + 1) return false;

        //if (other.pos.y + other.height <= pos.y - height || other.pos.y - other.height >= pos.y + height) return false;
        return true;
        //return (other.pos.y + other.height > pos.y - height && other.pos.y - other.height < pos.y + height);
    }
    */


    public boolean isCollidingWithOtherRect(Rect other){    
        if( ((int)(this.pos.x) + this.width <= (int)(other.pos.x) - other.width) || ((int)(this.pos.x) - this.width >= (int)(other.pos.x) + other.width)) return false;
        if( ((int)(this.pos.y) + this.height <= (int)(other.pos.y) - other.height) || ((int)(this.pos.y) - this.height >= (int)(other.pos.y) + other.height)) return false;
        return true;
    }
 


    /**
     *  inverse la largeur et la hauteur
     */
    public void swapDimensions(){
        int temp = height;
        height = width;
        width = temp;
    }

    public void draw(Color color){
        Vector2 tl = getTLcorner();
        RayClass.rlj.shapes.DrawRectangle((int)tl.x, (int)tl.y, width<<1, height<<1, color);
    }

    public void drawSides(Color color){
        Vector2 coins[] = new Vector2[4];
        getAllCorners(coins);

        RayClass.rlj.shapes.DrawLineV(coins[0], coins[1], color);
        RayClass.rlj.shapes.DrawLineV(coins[1], coins[2], color);
        RayClass.rlj.shapes.DrawLineV(coins[2], coins[3], color);
        RayClass.rlj.shapes.DrawLineV(coins[3], coins[0], color);

    }

    public static Rect setRect(Vector2 pos, int rot, int width, int height){
        Rect rect = new Rect(pos, width, height);
        if(rot == 90 || rot == -90)  rect.swapDimensions();    //dimensions du rect selon la rotation

        if(GUI.placement_libre == false || GUI.entitySel.getOptionSelected() == GUI.MODE_EDIT_TILEGRAPHIQUE){ //placement du rect selon les dimensions
            rect.pos.x -= ((rect.width/32) % 2 == 0) ? 32 : 0;
            rect.pos.y -= ((rect.height/32) % 2 == 0) ? 32 : 0;
        }   
        return rect;
    }


/* 
    public void setRectRot(Vector2 pos, int rot, int width, int height){
        this.width
        if(rot == 90 || rot == -90)  rect.swapDimensions();    //dimensions du rect selon la rotation

        if(GUI.placement_libre == false){                           //placement du rect selon les dimensions
            rect.pos.x -= ((rect.width/32) % 2 == 0) ? 32 : 0;
            rect.pos.y -= ((rect.height/32) % 2 == 0) ? 32 : 0;
        }   
    }*/
}
