package src.entities;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

import src.Input;
import src.TxtrStrg;
import src.formes.Rect;

public class TileColl {
    private static Color transparent = new Color(255, 255, 255, 100);
    private static Color sideColor = new Color(200, 255, 200, 255);

    public Rect rect;
    public int rot;
    public int type;

    public TileColl(int type, Vector2 pos, int rot){
        rect = new Rect(pos, 32, 32);
        this.rot = rot;
        this.type = type;
    }

    public boolean isCursorIn(){
        return rect.isVectorInRect(Input.getCursorPosCam());
    }

    public void draw(){
        Texture2D txtr = TxtrStrg.getTileCollTxtr(type);
        Rectangle dest = new Rectangle(rect.pos, txtr.width, txtr.height);
        Rectangle src = new Rectangle(new Vector2(), txtr.width, txtr.height);
        Vector2 origin = new Vector2((int)txtr.width>>1, (int)txtr.height>>1);
        rTextures.DrawTexturePro(txtr, src, dest, origin, rot, transparent);
    }

    public void draw_transparent(){

        rect.drawSides(sideColor);
        /*Texture2D txtr = TxtrStrg.getTileCollTxtr(type);
        Rectangle dest = new Rectangle(rect.pos, txtr.width, txtr.height);
        Rectangle src = new Rectangle(new Vector2(), txtr.width, txtr.height);
        Vector2 origin = new Vector2((int)txtr.width>>1, (int)txtr.height>>1);
        rTextures.DrawTexturePro(txtr, src, dest, origin, rot, new Color(255, 255, 255, transparency));
        */
    }

    public String writeToFile(){
        String s = "";
        s += (int)rect.pos.x + " ";
        s += (int)rect.pos.y + " ";
        s += rot + "\n";
        return s;
    }

}
