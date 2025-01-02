package src.entities;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.raylib.java.raymath.Vector2;
import src.Input;
import src.entities.actors.Actor;
import src.gui.ActorEditableOptions;
import src.gui.GUI;

public class LvlEle {
   // public static Rect tileRect = new Rect(Input.getCursorPosCase(), 32, 32);
    private static List<Actor> actors = new ArrayList<>();
    private static TileGraphicArray tileGraphics_layer1 = new TileGraphicArray();
    private static TileGraphicArray tileGraphics_layer2 = new TileGraphicArray();
    private static List<TileColl> tileColls = new ArrayList<>();
    
    public static GameCam camEntity = new GameCam();  //camera du jeu sprld
    
    private static Optional<Actor> actorSelected = Optional.empty();

    private static final int 
    NO_ACTION = 0,
    ACTOR_SELECTION = 1,
    ACTOR_DEPLACEMENT = 2;
    
    public static int lvlEditAction = NO_ACTION;
    
    public static List<Actor> getActors(){
        return actors;
    }

    public static  TileGraphicArray getTileGraphics_layer1(){
        return tileGraphics_layer1;
    }

    public static TileGraphicArray getTileGraphics_layer2(){
        return tileGraphics_layer2;
    }

    public static void tileGraphics_layer1_writeToFile(BufferedWriter file){
        tileGraphics_layer1.writeToFile(file);
    }
    public static void tileGraphics_layer2_writeToFile(BufferedWriter file){
        tileGraphics_layer2.writeToFile(file);
    }

    public static List<TileColl> getTileColls(){
        return tileColls;
    }


    public static Optional<Actor> getActorSelected(){
        return actorSelected;
    }

    public static void upd(){

        switch(GUI.entitySel.getOptionSelected()){
            case GUI.MODE_EDIT_ACTEUR :{
                editionActeur();
            }break;

            case GUI.MODE_EDIT_TILEGRAPHIQUE :{
                editionTileGraphique();
            }break;

            case GUI.MODE_EDIT_TILECOLLISION : {
                editionTileColl();
            }break;

        }        
    }



    private static void editionActeur(){
        if (GUI.isMousePressedInGUI()) return;
        
            
        editionActeurCliqueGauche();
            
        if(Input.isMouseRightDown() ) deleteActor();
    }


    private static void editionActeurCliqueGauche(){

        switch(lvlEditAction){
            
            case NO_ACTION :        //lorsque souris non pressée
                if(!Input.isMouseLeftPressed()) return;

                actorSelected = getActor();

                if (actorSelected.isEmpty()) {
                    addActor();
                }
                else {
                    lvlEditAction = ACTOR_SELECTION;
                    GUI.actorEditableOptions = new ActorEditableOptions(actorSelected.get().getOptions());
                }
                
            break;


            case ACTOR_SELECTION : 
                if(!Input.isMouseLeftPressed()) return;
                Optional<Actor> newActorSelected = getActor();
                
                if (newActorSelected.isEmpty()){              //le curseur n'est sur aucun acteur
                    actorSelected = Optional.empty();
                    lvlEditAction = NO_ACTION;
                }
                else if(actorSelected.equals(newActorSelected)){ //le curseur est sur l'acteur déjà sélectionné
                    lvlEditAction = ACTOR_DEPLACEMENT;    
                }

                else {
                    actorSelected = newActorSelected;       //le curseur est sur un acteur différent
                    GUI.actorEditableOptions = new ActorEditableOptions(actorSelected.get().getOptions());
                }

            break;


            case ACTOR_DEPLACEMENT : 
                if(Input.isMouseLeftReleased()) {
                    lvlEditAction = ACTOR_SELECTION;  
                    return;
                }
                Vector2 cursorDeltaCam = Input.getFreeOrCaseCursorDeltaCam();
                actorSelected.ifPresent((actor) -> {
                    actor.rect.pos.x += cursorDeltaCam.x;
                    actor.rect.pos.y += cursorDeltaCam.y;
                });
            break;

        }
    }


    private static Optional<Actor> getActor(){
        Iterator<Actor> it = actors.iterator();
        while(it.hasNext()){
            Actor currActor = it.next();
            if(currActor.isCursorIn()) return Optional.of(currActor);
        }
        return Optional.empty();
    }

    private static void addActor(){
        Actor toAdd = Actor.create(GUI.acteurSel.getOptionSelected(), Input.getFreeOrCaseCursorPosCam(), GUI.getCurrRot());
        int i = 0;
        while(i < actors.size() && actors.get(i).getId() < toAdd.getId()) i++;
        actors.add(i, toAdd);            
    }

    private static void deleteActor(){
        Iterator<Actor> it = actors.iterator();
        while(it.hasNext()){
            Actor curr = it.next();
            if(curr.isCursorIn()){
                if(actorSelected.isPresent() && curr == actorSelected.get()) {
                    actorSelected = Optional.empty();
                    lvlEditAction = NO_ACTION;
                }
                it.remove();
                return;
            }
        }
    }

    private static void editionTileGraphique(){
        if(Input.isMouseLeftDown()  && !GUI.isMousePressedInGUI()) addTileGraphic();
        if(Input.isMouseRightDown() && !GUI.isMousePressedInGUI()) deleteTileGraphic();

    }


    /*private static void updTileRect(){
        int decor_id = GUI.tileGraphicSel.getCurrOptionSelected();
        Texture2D txtr = TxtrStrg.getTileGraphicTxtr(decor_id);
        tileRect = Rect.setRect(Input.getCursorPosCase(), decor_id, txtr.getWidth(), txtr.getHeight());
    }*/

    private static void addTileGraphic(){
        
        Vector2 cursorPosCase = Input.getCursorPosCase();
        
        TileGraphic toadd = new TileGraphic(GUI.tileGraphicSel.getCurrOptionSelected(), cursorPosCase, GUI.getCurrRot());

        if(GUI.currLayer == 1)  tileGraphics_layer1.add(toadd);
        else                    tileGraphics_layer2.add(toadd);
    }

    private static void deleteTileGraphic(){
        if(GUI.currLayer == 1){
            tileGraphics_layer1.remove();
            return;
        }
        tileGraphics_layer2.remove();
    }




    private static void editionTileColl(){
        if(Input.isMouseLeftDown()  && !GUI.isMousePressedInGUI()) addTileColl();
        if(Input.isMouseRightDown() && !GUI.isMousePressedInGUI()) deleteTileColl();

    }

    private static void addTileColl(){
        Vector2 cursorPosCase = Input.getCursorPosCase();
           
        int i = 0;
        TileColl curr = null;
        while(i < tileColls.size()){
            curr = tileColls.get(i);
            if(curr.rect.isVectorInRect(cursorPosCase)){
                return;
            }
            if(curr.type > GUI.tileCollSel.getCurrOptionSelected()) {break;}
            i++;
        }

        tileColls.add(i, new TileColl(GUI.tileCollSel.getCurrOptionSelected(), cursorPosCase, GUI.getCurrRot()));
    }





    private static void deleteTileColl(){
        Iterator<TileColl> it = tileColls.iterator();
        while(it.hasNext()){
            TileColl curr = it.next();    
            if(curr.isCursorIn()){
                it.remove();
                return;
            }
        }
    }


    public static void empty(){
        actors =  new ArrayList<>();
        tileGraphics_layer1 = new TileGraphicArray();
        tileGraphics_layer2 = new TileGraphicArray();
        tileColls = new ArrayList<>();
        camEntity = new GameCam();
    }


    public static void draw(){    
        if(GUI.drawOtherEntities == GUI.DRAW_OPAQUE){
            drawTileGraphic();
            drawActors();
            drawSelectedActors();
            drawTileColl();
        }
        //DRAW_TRANSPARENT
        else {
            switch(GUI.entitySel.getOptionSelected()){
                case GUI.MODE_EDIT_ACTEUR :
                    drawTileGraphic_transparent();
                    drawTileColl_transparent();
                    drawActors();
                    drawSelectedActors();
                break;
                case GUI.MODE_EDIT_TILECOLLISION :
                    drawTileGraphic_transparent();
                    drawActors_transparent();
                    drawTileColl();
                break;
                case GUI.MODE_EDIT_TILEGRAPHIQUE :
                    drawTileGraphic_transparent();
                    drawTileColl_transparent();
                    drawActors_transparent();
                break;
            }
        }
        camEntity.draw();   
        
    }


    private static void drawSelectedActors(){
        actorSelected.ifPresent((actor) -> actor.drawSelectionRect());
    }

    private static void drawActors(){
        Iterator<Actor> it = actors.iterator();
        while(it.hasNext()) it.next().draw();
    }

    private static void drawActors_transparent(){
        Iterator<Actor> it = actors.iterator();
        while(it.hasNext()) it.next().drawTransparent(70);
    }



    private static void drawTileGraphic(){
        tileGraphics_layer2.draw();
        tileGraphics_layer1.draw();
    }

    private static void drawTileGraphic_transparent(){
        if(GUI.currLayer == 1){
            tileGraphics_layer2.draw_transparent(100);
            tileGraphics_layer1.draw();
        }
        else{
            tileGraphics_layer1.draw_transparent(100);
            tileGraphics_layer2.draw();
            
        }
    }


    private static void drawTileColl(){
        Iterator<TileColl> it = tileColls.iterator();
        while(it.hasNext()) it.next().draw();
    }

    private static void drawTileColl_transparent(){
        Iterator<TileColl> it = tileColls.iterator();
        while(it.hasNext()) it.next().draw_transparent();
    }

}
