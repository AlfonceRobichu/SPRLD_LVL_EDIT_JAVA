package src.visitor;

import src.gui.GUIvisitor;

public interface Element {

    void accept(GUIvisitor visitor);
}
