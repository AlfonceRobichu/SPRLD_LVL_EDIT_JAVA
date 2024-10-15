package src.selectionTile;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

import src.Input;
import src.RayClass;
import src.TxtrStrg;

public class SelectionTileGraphic extends SelectionTile{


    private static int txtrDisplaySize = 64;
    private static int space = 6;
    
    public SelectionTileGraphic(){
        super(400, 4);
    }

    

    public void upd(){
        if(!Input.isMouseLeftPressed()) return;
        int ecartspace = txtrDisplaySize + space;
        Rectangle rect = new Rectangle(x, y, txtrDisplaySize, txtrDisplaySize);
        for(int i = 0; i * nbcolumns < TxtrStrg.getTileGraphicTxtrNb() ; i++){
            for(int j = 0; j < nbcolumns  &&  (i*nbcolumns) + j < TxtrStrg.getTileGraphicTxtrNb() ; j++){       
                rect.x = x + (j*ecartspace);
                rect.y = y + (i*ecartspace);
                if(RayClass.rlj.shapes.CheckCollisionPointRec(Input.getCursorPos(), rect)){
                    currOptionSelected = i * nbcolumns + j;
                    return;
                }
            }
        }
    }



    public void draw(){
        //System.out.println("affiche");

        //Texture2D test = TxtrStrg.getTileGraphicTxtr(3);
        //RayClass.rlj.textures.DrawTexture(test, 0, 500, Color.WHITE);
        int ecartspace = txtrDisplaySize + space;
        Texture2D curr;
        for(int i = 0; i * nbcolumns < TxtrStrg.getTileGraphicTxtrNb() ; i++){
            for(int j = 0; j < nbcolumns  &&  (i*nbcolumns) + j < TxtrStrg.getTileGraphicTxtrNb()  ; j ++){        
                curr = TxtrStrg.getTileGraphicTxtr(i*nbcolumns + j);
                Rectangle src = new Rectangle(0, 0, curr.width, curr.height);
                Rectangle dest = new Rectangle(x + (j * ecartspace), y + (ecartspace * i), txtrDisplaySize, txtrDisplaySize);
                rTextures.DrawTexturePro(curr, src, dest, new Vector2() , 0.0f, Color.WHITE);
            }
        }
    }

}
