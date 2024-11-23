package src.gui_commands;

import src.entities.LvlEle;
import src.gui.GUI;

public class CommandNewFile implements Command{
    
    public void execute(){
        LvlEle.empty();
        GUI.resetReglages();
    }

}
