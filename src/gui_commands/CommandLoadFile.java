package src.gui_commands;

import src.Scene;

public class CommandLoadFile implements Command{

    public void execute(){
        Scene.changeScene(Scene.FILELOAD);
    }

}
