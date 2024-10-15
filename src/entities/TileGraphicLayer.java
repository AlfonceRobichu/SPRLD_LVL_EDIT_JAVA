package src.entities;

import src.TxtrStrg;
import src.gui.GUI;

public class TileGraphicLayer { //a enlever
    private int decor_id;
    private int layer;

    public static TileGraphicLayer tileGraphicLayers[];


    public int get_decor_id(){
        return decor_id;
    }

    public int get_layer(){
        return layer;
    }


    private TileGraphicLayer(int decor_id, int layer){
        this.decor_id = decor_id;
        this.layer = layer;

    }

    public TileGraphicLayer(){
        tileGraphicLayers = new TileGraphicLayer[TxtrStrg.getTileGraphicTxtrNb()];
        int i = 0;
        switch(GUI.currFg.getOptionSelected()){
            
            default : {
                tileGraphicLayers[i++] = new TileGraphicLayer(0, 1);
                tileGraphicLayers[i++] = new TileGraphicLayer(1, 1);
                tileGraphicLayers[i++] = new TileGraphicLayer(2, 1);
                tileGraphicLayers[i++] = new TileGraphicLayer(3, 1);
                tileGraphicLayers[i++] = new TileGraphicLayer(4, 1);
                tileGraphicLayers[i++] = new TileGraphicLayer(5, 1);
                tileGraphicLayers[i++] = new TileGraphicLayer(6, 1);
                tileGraphicLayers[i++] = new TileGraphicLayer(7, 1);
                tileGraphicLayers[i++] = new TileGraphicLayer(8, 1);
                tileGraphicLayers[i++] = new TileGraphicLayer(9, 1);
                tileGraphicLayers[i++] = new TileGraphicLayer(10, 1);
                tileGraphicLayers[i++] = new TileGraphicLayer(11, 1);
                tileGraphicLayers[i++] = new TileGraphicLayer(12, 1);
                //tileGraphicLayers[i++] = new TileGraphicLayer(13, 2);

            }
        }
    }




}
