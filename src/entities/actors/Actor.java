package src.entities.actors;


import java.util.List;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

import src.Input;
import src.RayClass;
import src.TxtrStrg;
import src.formes.Rect;
import src.otherRealTimeValueInput.RealTimeValue;

public abstract class Actor {
    protected int id;
    protected int ogRot;
    protected boolean lookRight;

    protected static int x = 0;
    protected static int y = 480;
    protected static int textSize = 20;
    protected static int boxWidth = 300;
    protected static int boxHeight = 30;
    protected static int optionsOffset = 30;

    //private Rectr rectr;
    public Rect rect;

    //ArrayList<Integer> args;
    public static final int ACTR_TYPE_NB = 10;

    public static String acteurSelOptions[] = init_acteurSelOptions();
            

    private Actor(float x, float y){
        id = 0;
        ogRot = 0;
        lookRight = true;
    }

    public Actor(int id, Vector2 pos, int rot, int width, int height){
        this.id = id;
        ogRot = rot;
        lookRight = true;
        rect = Rect.createRectFromRot(pos, rot, width, height);
    }

    public static Actor create(int id, Vector2 pos, int rot){
        switch(id){     //dimensions du rectangle selon le type d'acteur
            case Sprld.ID : return new Sprld(id, pos, rot);

            case Cog.ID : return new Cog(id, pos, rot);
            
            case MvPlatform.ID : return new MvPlatform(id, pos, rot);
            case Bumper.ID :  return new Bumper(id, pos, rot);
        }
        return new Sprld(id, pos, rot);
    }   

    public int getId(){
        return id;
    }

    public int getOgRot(){
        return ogRot;
    }

    public boolean getLookRight(){
        return lookRight;
    }



    public boolean isCursorIn(){
        return rect.isVectorInRect(Input.getCursorPosCam());
    }


    private static String[] init_acteurSelOptions(){
        String[] res = new String[ACTR_TYPE_NB];
        for(int i = 0; i < res.length; i++) res[i] = "unused";


        res[Sprld.ID] = Sprld.name;
        res[Cog.ID] = Cog.name;
        res[MvPlatform.ID] = MvPlatform.name;
        res[Bumper.ID] = Bumper.name;
        return res;
    }


    public void draw(){
        rect.drawSides(Color.RED);
        
       
        Texture2D txtr = TxtrStrg.getActorTxtr(id);
        if(txtr == null) return;
        Rectangle dest = new Rectangle(rect.pos, txtr.width, txtr.height);
        Rectangle src = new Rectangle(new Vector2(), txtr.width, txtr.height);
        Vector2 origin = new Vector2((int)txtr.width>>1, (int)txtr.height>>1);

        //System.out.println("test" + (int)(rectr.rot * Utilities.TODEG));

        rTextures.DrawTexturePro(txtr, src, dest, origin, ogRot, Color.WHITE);
        //rectr.draw(Color.RED);
    }

    public void drawSelectionRect(){
        rect.drawSides(Color.YELLOW);
    }

    public void drawTransparent(int transparency){
        rect.draw(new Color(10, 10, 255, transparency));
        
        //rectr.draw(Color.RED);
    }


    public abstract List<RealTimeValue> getOptions();

    protected List<RealTimeValue> getAbstractOptions(){
        return List.of();
    }

    
    public abstract void displayInfo(); //TODO : OBSOLETE

    protected int displayInfo(String name){ //TODO : OBSOLETE
        RayClass.rlj.text.DrawText(name, x, y, textSize, Color.WHITE);
        int offset = y + optionsOffset;

        RayClass.rlj.text.DrawText("lookRight", x, offset, textSize, Color.WHITE);
        offset += optionsOffset;
        RayClass.rlj.shapes.DrawRectangle(x , offset, boxWidth, boxHeight, RayClass.rlj.textures.GetColor(0x303030FF));
        RayClass.rlj.shapes.DrawRectangleLines(x ,offset, boxWidth, boxHeight, Color.LIGHTGRAY);
        RayClass.rlj.text.DrawText(""+lookRight, x, offset, textSize, Color.WHITE);
        return offset;
    }


    
    
    public String writeToFile(){
        int lookRightInt = lookRight  ? 1 : 0;
        String s = "";
        s += (int)rect.pos.x + " ";
        s += (int)rect.pos.y + " ";
        s +=      ogRot   + " ";
        s += lookRightInt + "\n";
        return s; 
    }


}
