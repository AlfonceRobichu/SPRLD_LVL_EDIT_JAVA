package src.gui;

import src.visitor.Element;

public abstract class GUIelement implements Element{

    public void accept(GUIvisitor visitor){
        visitor.visit(this);
    }

}
