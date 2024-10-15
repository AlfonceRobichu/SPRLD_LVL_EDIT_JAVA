package src.entities.actors;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;

import src.RayClass;
import src.formes.Rect;
import src.realTimeValueInput.RealTimeValueInputArea;
import src.realTimeValueInput.StringRealTimeInput;

public class Cog extends Actor {
    public static final int ID = 1;
    public static final String name = "cog";
    
    public RealTimeValueInputArea rotSpd;
    //public float rotSpd = 0.2f;


    public Cog(int id, Vector2 pos, int rot){
        super(id, pos, rot);
        super.rect = Rect.setRect(pos, rot, 128, 128);
        rotSpd = new RealTimeValueInputArea("RotSpd", super.displayInfo(name), new StringRealTimeInput("0.0f"));
        //rotSpd = new FloatRealTimeInput("0.2");
    }

    @Override
    public void updInfo(){
        rotSpd.upd();
        //Rectangle selRect = new Rectangle(x, y, width, height);
    }


    public void displayInfo(){
        int offset = super.displayInfo(name);

        offset += optionsOffset;

        rotSpd.draw();
/*
        RayClass.rlj.text.DrawText("rotSpd", x, offset, textSize, Color.WHITE);
        offset += optionsOffset;
        RayClass.rlj.shapes.DrawRectangle(x, offset, boxWidth, boxHeight, RayClass.rlj.textures.GetColor(0x303030FF));
        RayClass.rlj.shapes.DrawRectangleLines(x, offset, boxWidth, boxHeight, Color.LIGHTGRAY);
        RayClass.rlj.text.DrawText(rotSpd.toString(), x, offset, textSize, Color.WHITE);
         */
    }
}
