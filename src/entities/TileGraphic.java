package src.entities;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

import src.Input;
import src.TxtrStrg;
import src.formes.Rect;

public class TileGraphic{
    private int partie_de_decor;
    public Rect rect;
    private int rot;

    public TileGraphic(int decor_id, Vector2 ogPos, int rot){
        this.partie_de_decor = decor_id;
        Texture2D txtr = TxtrStrg.getTileGraphicTxtr(decor_id);
        this.rot = rot;

        this.rect = Rect.setRect(ogPos, rot, txtr.getWidth()>>1, txtr.getHeight()>>1);
        /*this.rect = new Rect(ogPos, txtr.getWidth()>>1, txtr.getHeight()>>1);

        


        if(rot == 90 || rot == -90) rect.swapDimensions();
        rect.pos.x -= ((rect.width/32) % 2 == 0)  ? 32 : 0;
        rect.pos.y -= ((rect.height/32) % 2 == 0) ? 32 : 0;
        */
    }






    public void update(int new_decor, int new_rot){
        partie_de_decor = new_decor;
        rot = new_rot;
        if(rot == 90 || rot == -90) rect.swapDimensions();
    }


    public int getPartie_de_decor(){
        return partie_de_decor;
    }


    public boolean isCursorIn(){
        return rect.isVectorInRect(Input.getCursorPosCase());  //verif sur boundbox
        
    }



    public void draw(){
        Texture2D txtr = TxtrStrg.getTileGraphicTxtr(partie_de_decor);
        Rectangle dest = new Rectangle(rect.pos, txtr.width, txtr.height);
        Rectangle src = new Rectangle(new Vector2(), txtr.width, txtr.height);
        Vector2 origin = new Vector2((int)txtr.width>>1, (int)txtr.height>>1);
        rTextures.DrawTexturePro(txtr, src, dest, origin, rot, Color.WHITE);
    }

    public void draw_transparent(int transparency){
        Texture2D txtr = TxtrStrg.getTileGraphicTxtr(partie_de_decor);
        Rectangle dest = new Rectangle(rect.pos, txtr.width, txtr.height);
        Rectangle src = new Rectangle(new Vector2(), txtr.width, txtr.height);
        Vector2 origin = new Vector2((int)txtr.width>>1, (int)txtr.height>>1);
        rTextures.DrawTexturePro(txtr, src, dest, origin, rot, new Color(255, 255, 255, transparency));

    }

    public String writeToFile(){
        String s = "";
        s += (int)rect.pos.x + " ";
        s += (int)rect.pos.y + " ";
        s += (int)rot + "\n";
        return s;
    }
}
