package src.gui_commands;

import src.gestionFichier.FichierIO;
import src.gui.GUI;

public class CommandSaveFile implements Command{

    public void execute(){
        FichierIO.saveLvlFile(GUI.currLvl);
    }

}
