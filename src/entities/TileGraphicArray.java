package src.entities;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.raylib.java.textures.Texture2D;

import java.util.Iterator;

import src.Input;
import src.TxtrStrg;
import src.formes.Rect;
import src.gui.GUI;

public class TileGraphicArray {

    public List<List<TileGraphic>> tileGraphics; // = new ArrayList<>();


    public TileGraphicArray(){
        tileGraphics = new ArrayList<>();
        for(int i = 0; i < TxtrStrg.getTileGraphicTxtrNb(); i++)
            tileGraphics.add(new ArrayList<>());
    }




    /**
     *  place l'itérateur sur le tile sur lequel se trouve le curseur
     * @return 
     */
    private Iterator<TileGraphic> get_iterator_on_tile_on_cursorPosCase(){
        ListIterator<List<TileGraphic>> it_list = tileGraphics.listIterator();

        while(it_list.hasNext()){
            List<TileGraphic> currArray = it_list.next();
            Iterator<TileGraphic> it = currArray.iterator();
            
            while(it.hasNext()){
                TileGraphic currTile = it.next();
                //Rect r = new Rect(Input.getCursorPosCase(), 0, 0);
                
                //currTile.rect.isCollidingWithOtherRect()
                if(currTile.rect.isVectorInRect(Input.getCursorPosCase())) return it;
            }
        }
        return null;
    }

    /**
     *  place l'itérateur sur le tile sur lequel se trouve le rectangle où sera placé le tile s'il
     * y a de la place
     * @return 
     */
    private Iterator<TileGraphic> get_iterator_on_tile_on_RectPosCase(Rect rect){
        ListIterator<List<TileGraphic>> it_list = tileGraphics.listIterator();

        while(it_list.hasNext()){
            List<TileGraphic> currArray = it_list.next();
            Iterator<TileGraphic> it = currArray.iterator();
            
            while(it.hasNext()){
                TileGraphic currTile = it.next();
                //Rect r = new Rect(Input.getCursorPosCase(), 0, 0);
                
                if(currTile.rect.isCollidingWithOtherRect(rect)) return it;
                //if(currTile.rect.isVectorInRect(Input.getCursorPosCase())) return it;
            }
        }
        return null;
    }



     /**
     *  retourne le tile sur lequel se trouve le curseur
     * @return 
     */
    private TileGraphic get_on_tile_on_cursorPosCase(){
        ListIterator<List<TileGraphic>> it_list = tileGraphics.listIterator();

        while(it_list.hasNext()){
            List<TileGraphic> currArray = it_list.next();
            Iterator<TileGraphic> it = currArray.iterator();
            
            while(it.hasNext()){
                TileGraphic currTile = it.next();
                if(currTile.rect.isVectorInRect(Input.getCursorPosCase())) return currTile;
            }
        }
        return null;
    }



    public void add(TileGraphic toadd){

        Texture2D txtr = TxtrStrg.getTileGraphicTxtr(GUI.tileGraphicSel.getCurrOptionSelected());
        
        Rect rect = Rect.setRect(Input.getCursorPosCase(), GUI.getCurrRot(), txtr.getWidth()>>1, txtr.getHeight()>>1);
        
        Iterator<TileGraphic> todel = get_iterator_on_tile_on_RectPosCase(rect);

        if(todel == null){
            tileGraphics.get(toadd.getPartie_de_decor()).add(toadd);    
        }

        
    }



    public void remove_alt(){
        ListIterator<List<TileGraphic>> it_list = tileGraphics.listIterator();

        while(it_list.hasNext()){
            List<TileGraphic> currArray = it_list.next();
            Iterator<TileGraphic> it = currArray.iterator();
            
            while(it.hasNext()){
                TileGraphic currTile = it.next();
                if(currTile.rect.isVectorInRect(Input.getCursorPosCase())){
                    it.remove();
                }
            }
        }
    }

    /**
     * place un itérateur sur le tile à enlever si un tel tile se trouve
     * sur le curseur et le supprime
     */
    public void remove(){
        Iterator<TileGraphic> it = get_iterator_on_tile_on_cursorPosCase();
        if(it != null) it.remove();

    }

    public int size(){
        int size = 0;
        ListIterator<List<TileGraphic>> it_list = tileGraphics.listIterator();
        while(it_list.hasNext()){
            size += it_list.next().size();
        }
        return size;
    }


    public void writeToFile(BufferedWriter fichier){
        try {
            ListIterator<List<TileGraphic>> it_list = tileGraphics.listIterator();
            while(it_list.hasNext()){       //pour chaque partie de décor
                List<TileGraphic> currList = it_list.next();
                Iterator<TileGraphic> itgraphic = currList.iterator();

                TileGraphic tg = null;
                if(itgraphic.hasNext()){    //s'il exsiste au moins un tile sur cette partie
                    tg = itgraphic.next();
                    fichier.write(">" + tg.getPartie_de_decor() + "\n");
                    fichier.write(tg.writeToFile());
                    
                }

                while(itgraphic.hasNext()){ //pour chaque tile
                    tg = itgraphic.next();
                    fichier.write(tg.writeToFile());
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
      



    


    public void draw(){
        ListIterator<List<TileGraphic>> it_list = tileGraphics.listIterator();
        while(it_list.hasNext()){
            List<TileGraphic> currArray = it_list.next();
            Iterator<TileGraphic> it = currArray.iterator();
            
            while(it.hasNext()){
                TileGraphic currTile = it.next();
                currTile.draw();
            }
        }
    }
    
    public void draw_transparent(int transparency){
        ListIterator<List<TileGraphic>> it_list = tileGraphics.listIterator();
        while(it_list.hasNext()){
            List<TileGraphic> currArray = it_list.next();
            Iterator<TileGraphic> it = currArray.iterator();
            
            while(it.hasNext()){
                TileGraphic currTile = it.next();
                currTile.draw_transparent(transparency);
            }
        }
    }


}
