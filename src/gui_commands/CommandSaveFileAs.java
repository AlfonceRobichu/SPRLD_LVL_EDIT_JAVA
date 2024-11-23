package src.gui_commands;

import src.Scene;

public class CommandSaveFileAs implements Command{

    public void execute(){
        Scene.changeScene(Scene.FILESAVE);
    }

}
