package src.realTimeValueInput;

public abstract class RealTimeValueInput implements RealTimeValueInputI {
    protected String input;
    protected String prevIntput;
    protected boolean active;


    public RealTimeValueInput(String s){
        active = false;
        input = s;
        prevIntput = s;
    }

    public boolean isActive(){
        return active;
    }
    
    /**
     * activation de la modification de l'entree,
     * l'entree precedente est gardee en memoire durant la modification
     */
    public void setActive(){
        active = true;
    }

    /**
     * recup de la valeur d'entree
     */
    public String getInput(){
        return input;
    }

    /**
     * 
     * @param s la valeur a setter
     */
    public void setInput (String s){
        input = s;
    }


    /**
     *  maj de la valeur d'entree
     */
    public abstract boolean upd();

    public void inputDone(){
        active = false;
        if(isInputEmpty()) input = prevIntput;
        else               prevIntput = input;
        
    }

    protected boolean isInputEmpty(){
        return input.isEmpty();
    }

}
