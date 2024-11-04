package src.gui;

public class BoolToogleValue extends GUIelement {

    private boolean value;

    public BoolToogleValue(boolean initValue){
        value = initValue;
    }

    public void toogleValue(){
        value = !value;
    }

    public boolean getValue(){
        return value;
    }

    @Override
    public String toString(){
        return value == true ? "1" : "0";
    }

}
