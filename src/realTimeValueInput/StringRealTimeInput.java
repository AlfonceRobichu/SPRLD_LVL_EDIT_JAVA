package src.realTimeValueInput;


import src.RayClass;

public class StringRealTimeInput extends RealTimeValueInput{

    public StringRealTimeInput(String s){
        super(s);
    }

    public StringRealTimeInput(){
        super("");
    }

    /**
     *  ATTENTION : Retourne True meme si le string de l'input est vide avec l'input de la valeur precedente (a changer ?)
     */
    @Override
    public boolean upd(){
        if(!isActive()) return false;
        int keyChar = RayClass.rlj.core.GetCharPressed();

        if(keyChar >= 39 && keyChar <= 125){
            input += (char)keyChar;
        }
        
        int len = input.length();
        if(len > 0 && RayClass.rlj.core.IsKeyPressed(259)){  //si retour appuye, on enleve le dernier caractere
            input = input.substring(0, len-1);
        }

        else if (RayClass.rlj.core.IsKeyPressed(257)){   //keyChar est 'entree/retour chariot'
            inputDone();
            return true;
        }
        return false;
    }

}
