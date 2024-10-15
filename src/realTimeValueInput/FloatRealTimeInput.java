package src.realTimeValueInput;

import src.RayClass;

public class FloatRealTimeInput extends RealTimeValueInput{

    public FloatRealTimeInput(){
        super("0.0");
    }

    public FloatRealTimeInput(String s){
        super(s);
    }

    
    @Override
    public boolean upd(){
        if(!active) return false;
        int keyChar = RayClass.rlj.core.GetCharPressed();
        int len = input.length();

        if(keyChar >= 48 && keyChar <= 57){
            input += (char)keyChar;
        }

        else if(keyChar == 45){ //appuye sur '-''
            if(input.charAt(0) == '-') input.substring(1);  //si valeur negative, elle devient positive
            else                    input = '-' + input;    //sinon elle devient negative
        }

        else if(keyChar == 46 && !input.contains(".")){ // 46 = '.' (le point) et qu'il n'y a pas déjà de point
            input += '.';
        }
        
        else if(len > 0 && RayClass.rlj.core.IsKeyPressed(259)){  //si retour appuye, on enleve le dernier caractere
            input = input.substring(0, len-1);
        }

        else if (RayClass.rlj.core.IsKeyPressed(257)){   //keyChar est 'entree/retour chariot'
            inputDone();
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return input;
    }
    


    public static void main(String[] args) {
        //FloatRealTimeInput var1 = new FloatRealTimeInput(3.0f);
        //System.out.println(var1);
    }

}
