package src.realTimeValueInput;

public interface RealTimeValueInputI {
    boolean isActive();

    void setActive();

    /**
     * recup de la valeur d'entree
     */
    String getInput();

    /**
     * 
     * @param s la valeur a setter
     */
    void setInput (String s);

        /**
     *  maj de la valeur d'entree
     */
    boolean upd();

    void inputDone();

}
