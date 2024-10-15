package src.selectionTile;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

import src.Input;
import src.RayClass;
import src.TxtrStrg;

public class SelectionTileColl extends SelectionTile{


    private static int txtrDisplaySize = 64;

    
    public SelectionTileColl(){
        super(400, 4);
    }

    public void upd(){
        if(!Input.isMouseLeftPressed()) return;
        
        Rectangle rect = new Rectangle(x, y, txtrDisplaySize, txtrDisplaySize);
        for(int i = 0; i * nbcolumns < TxtrStrg.getTileCollTxtrNb() ; i++){
            for(int j = 0; j < nbcolumns  &&  (i*nbcolumns) + j < TxtrStrg.getTileCollTxtrNb() ; j++){       
                rect.x = x + (j*txtrDisplaySize);
                rect.y = y + (i*txtrDisplaySize);
                if(RayClass.rlj.shapes.CheckCollisionPointRec(Input.getCursorPos(), rect)){
                    currOptionSelected = i * nbcolumns + j;
                    return;
                }
            }
        }
    }



    public void draw(){

        Texture2D curr;
        for(int i = 0; i * nbcolumns < TxtrStrg.getTileCollTxtrNb() ; i++){
            for(int j = 0; j < nbcolumns  &&  (i*nbcolumns) + j < TxtrStrg.getTileCollTxtrNb()  ; j ++){        
                curr = TxtrStrg.getTileCollTxtr(i*nbcolumns + j);
                Rectangle src = new Rectangle(0, 0, curr.width, curr.height);
                Rectangle dest = new Rectangle(x + (j * txtrDisplaySize), y + (txtrDisplaySize * i), txtrDisplaySize, txtrDisplaySize);
                rTextures.DrawTexturePro(curr, src, dest, new Vector2() , 0.0f, Color.WHITE);
            }
        }
    }

}
