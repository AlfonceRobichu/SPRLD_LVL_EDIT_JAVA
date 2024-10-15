package src.gestionFichier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import com.raylib.java.raymath.Vector2;

import src.entities.GameCam;
import src.entities.LvlEle;
import src.entities.TileColl;
import src.entities.TileGraphic;
import src.entities.TileGraphicLayer;
import src.entities.actors.Actor;
import src.gui.GUI;

public class FichierIO {


    private static String lvlFilePath  = "resource\\levels\\";

    public static String getLvlFilePath(){
        return lvlFilePath;
    }

    public static void loadLvlFile(String nomFichier){
        LvlEle.empty();
        FileReader fr;
        try {
            fr = new FileReader(lvlFilePath + nomFichier );
       
       
            BufferedReader fichier = new BufferedReader(fr);
        
            String line;
            try {
                line = fichier.readLine();
                GUI.currMusic.setOptionSelected(Integer.parseInt(lineSplit(line)[1]));
                line = fichier.readLine();
                GUI.currBg.setOptionSelected(Integer.parseInt(lineSplit(line)[1]));

                line = fichier.readLine();
                
                String[] lineTab = lineSplit(line);

                LvlEle.camEntity = new GameCam(new Vector2(Integer.parseInt(lineTab[1]), Integer.parseInt(lineTab[2])), 
                                            Integer.parseInt(lineTab[3]));
                

                fichier.readLine();         //passe actors

                int dataId = 0;
                int layer = 0;

                line = fichier.readLine();   
                //acteurs
            
                while(line.charAt(0) != 'T'){
                    if(line.charAt(0) == '>') dataId = Integer.parseInt(line.substring(1));
                    else    addActor(dataId, line);
                    line = fichier.readLine();
                }
                
                //tiles graphiques
                GUI.currFg.setOptionSelected(Integer.parseInt(lineSplit(line)[1]));    //lecture type de decor
                

                line = fichier.readLine();
                new TileGraphicLayer();
                while(line.charAt(0) != 'T'){
                    if(line.charAt(0) == '>') dataId = Integer.parseInt(line.substring(1));
                    else if(line.charAt(0) == 'L') layer++;
                    else    addTileGraphic(dataId, layer, line);
                    line = fichier.readLine();
                }
                
                line = fichier.readLine();
                //tile collision
                while(line != null &&  line.charAt(0) != 'T' ){
                    if(line.charAt(0) == '>') dataId = Integer.parseInt(line.substring(1));
                    else    addTileColl(dataId, line);
                    line = fichier.readLine();
                }

        
                
                fichier.close();
                fr.close();
                GUI.currLvl = nomFichier;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String[] lineSplit(String line){
        return line.split("[\\s\\n]+");
    }

    private static void addActor(int dataId, String line){
        String args[] = line.split("[\\s\\n]+");
        Vector2 v = new Vector2(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        int rot = Integer.parseInt(args[2]);
        LvlEle.getActors().add(Actor.create(dataId, v, rot));
    }

    private static void addTileGraphic(int dataId, int layer, String line){
        String args[] = line.split("[\\s\\n]+");
        Vector2 v = new Vector2(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        int rot = Integer.parseInt(args[2]);
        if(layer == 1)  LvlEle.getTileGraphics_layer1().add(new TileGraphic(dataId, v, rot));
        else            LvlEle.getTileGraphics_layer2().add(new TileGraphic(dataId, v, rot));
    }

    private static void addTileColl(int dataId, String line){
        String args[] = line.split("[\\s\\n]+");
        Vector2 v = new Vector2(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        int rot = Integer.parseInt(args[2]);
        LvlEle.getTileColls().add(new TileColl(dataId, v, rot));
    }


    public static void saveLvlFile(String nomFichier){
        try {
            FileWriter fw = new FileWriter(lvlFilePath + nomFichier);

            BufferedWriter fichier = new BufferedWriter(fw);

            fichier.write("MUS " + GUI.currMusic.getOptionSelected() + "\n");
            fichier.write("BG " + GUI.currBg.getOptionSelected() + "\n");
            
            fichier.write("CAM ");
            fichier.write(LvlEle.camEntity.toString());
            

            fichier.write("AC\n");
            Iterator<Actor> itactor = LvlEle.getActors().iterator();
            int id_prec = -1;
            Actor actor = null;
            if(itactor.hasNext()){
                actor = itactor.next();
                id_prec = actor.getId();
                fichier.write(">" + actor.getId() + "\n");
                fichier.write(actor.writeToFile());

            }
            
            while(itactor.hasNext()){
                actor = itactor.next();
                if(actor.getId() != id_prec){
                    id_prec = actor.getId();
                    fichier.write(">" + id_prec + "\n");
                }
                fichier.write(actor.writeToFile());
            }
            
    
            
            fichier.write("TG " + GUI.currFg.getOptionSelected() + "\n");
            fichier.write("L1 " + LvlEle.getTileGraphics_layer1().size() + "\n");
            LvlEle.tileGraphics_layer1_writeToFile(fichier);
            
            fichier.write("L2 " + LvlEle.getTileGraphics_layer2().size() + "\n");
            LvlEle.tileGraphics_layer2_writeToFile(fichier);

            
            
            fichier.write("TC " + LvlEle.getTileColls().size() + "\n");
            Iterator<TileColl> itcoll = LvlEle.getTileColls().iterator();
            TileColl tc = null;
            if(itcoll.hasNext()){
                tc = itcoll.next();
                id_prec = tc.type;
                fichier.write(">" + tc.type + "\n");
                fichier.write(tc.writeToFile());

            }
            while(itcoll.hasNext()){
                tc = itcoll.next();
                if(tc.type != id_prec){
                    id_prec = tc.type;
                    fichier.write(">" + tc.type + "\n");
                }
                fichier.write(tc.writeToFile());
            }



            fichier.close();
            fw.close();
            GUI.currLvl = nomFichier;
        } catch (IOException e) {

            
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }




}
