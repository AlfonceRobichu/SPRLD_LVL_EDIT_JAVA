package src;

import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

import src.entities.actors.Actor;
import src.entities.actors.Bumper;
import src.entities.actors.Cog;
import src.entities.actors.MvPlatform;
import src.entities.actors.Sprld;

public class TxtrStrg {
    private static Texture2D tileGraphicTxtrs[];
    private static Texture2D actorTxtrs[];
    private static Texture2D tileCollTxtrs[];
    


    public TxtrStrg(){
        loadActorsTxtrs();
        loadFg(0);
        loadTileCollTxtrs();
        //tileCollTxtrs = null;
    }

    private static void loadActorsTxtrs(){
        actorTxtrs = new Texture2D[Actor.ACTR_TYPE_NB];
        for(int i = 0; i < Actor.ACTR_TYPE_NB; i++) actorTxtrs[i] = null;
        actorTxtrs[Sprld.ID] = rTextures.LoadTexture("resource\\actors\\sprld.png");
        actorTxtrs[Cog.ID] = rTextures.LoadTexture("resource\\actors\\cog.png");
        actorTxtrs[MvPlatform.ID] = rTextures.LoadTexture("resource\\actors\\pltfrm_mv0.png");
        actorTxtrs[Bumper.ID] = rTextures.LoadTexture("resource\\actors\\bumper.png");

    }


    public static void loadFg(int fgID){
        int i = 0;
        System.out.println("chargement textures tileGraphiques");
        switch(fgID){
            case 1 :
            tileGraphicTxtrs = new Texture2D[4];    
            
            tileGraphicTxtrs[i++] = rTextures.LoadTexture("resource\\tilegraphics\\set1\\w2_br_c.png");
            tileGraphicTxtrs[i++] = rTextures.LoadTexture("resource\\tilegraphics\\set1\\w2_e.png");
            tileGraphicTxtrs[i++] = rTextures.LoadTexture("resource\\tilegraphics\\set1\\w2_m_tb.png");
            tileGraphicTxtrs[i++] = rTextures.LoadTexture("resource\\tilegraphics\\set1\\w2_c_0.png");

            break;

            default :   //case 0
            
            tileGraphicTxtrs = new Texture2D[14];    
            
            tileGraphicTxtrs[i++] = rTextures.LoadTexture("resource\\tilegraphics\\set0\\w0_c_tl.png");
            tileGraphicTxtrs[i++] = rTextures.LoadTexture("resource\\tilegraphics\\set0\\w0_c_tr.png");
            tileGraphicTxtrs[i++] = rTextures.LoadTexture("resource\\tilegraphics\\set0\\w0_c_bl.png");
            tileGraphicTxtrs[i++] = rTextures.LoadTexture("resource\\tilegraphics\\set0\\w0_c_br.png");
            
            tileGraphicTxtrs[i++] = rTextures.LoadTexture("resource\\tilegraphics\\set0\\w0_e_tl.png");
            tileGraphicTxtrs[i++] = rTextures.LoadTexture("resource\\tilegraphics\\set0\\w0_e_tr.png");
            tileGraphicTxtrs[i++] = rTextures.LoadTexture("resource\\tilegraphics\\set0\\w0_e_bl.png");
            tileGraphicTxtrs[i++] = rTextures.LoadTexture("resource\\tilegraphics\\set0\\w0_e_br.png");
            
            tileGraphicTxtrs[i++] = rTextures.LoadTexture("resource\\tilegraphics\\set0\\w0_s_l.png");
            tileGraphicTxtrs[i++] = rTextures.LoadTexture("resource\\tilegraphics\\set0\\w0_s_r.png");
            tileGraphicTxtrs[i++] = rTextures.LoadTexture("resource\\tilegraphics\\set0\\w0_s_t.png");
            tileGraphicTxtrs[i++] = rTextures.LoadTexture("resource\\tilegraphics\\set0\\w0_s_b.png");
    
            tileGraphicTxtrs[i++] = rTextures.LoadTexture("resource\\tilegraphics\\set0\\w0_m0.png");
            tileGraphicTxtrs[i++] = rTextures.LoadTexture("resource\\tilegraphics\\set0\\barbed_single.png");
            break;
        }

    }


    public static void loadTileCollTxtrs(){
        tileCollTxtrs = new Texture2D[4];
        tileCollTxtrs[0] = rTextures.LoadTexture("resource\\tilecolls\\tilecoll_side_nrml.png");
        tileCollTxtrs[1] = rTextures.LoadTexture("resource\\tilecolls\\tilecoll_edge_nrml.png");
        tileCollTxtrs[2] = rTextures.LoadTexture("resource\\tilecolls\\tilecoll_side_slpry.png");
        tileCollTxtrs[3] = rTextures.LoadTexture("resource\\tilecolls\\tilecoll_ddly.png");
    }

    public static Texture2D getActorTxtr(int txtrID){
        return actorTxtrs[txtrID];
    }



    public static Texture2D getTileGraphicTxtr(int txtrID){
        return tileGraphicTxtrs[txtrID];
    }

    public static int getTileGraphicTxtrNb(){
        return tileGraphicTxtrs.length;
    }

    public static Texture2D getTileCollTxtr(int txtrID){
        return tileCollTxtrs[txtrID];
    }

    public static int getTileCollTxtrNb(){
        return tileCollTxtrs.length;
    }

}
