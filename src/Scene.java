package src;

public class Scene {
    public static final int 
    LOAD = 0, 
    PLAY = 1,
    UNLOAD = 2;

    public static final int 
    EDIT = 0,
    FILELOAD = 1,
    FILESAVE = 2;

    public static int currScn = EDIT;
    public static int nextScn = EDIT;
    public static int currState = LOAD;

    public static void changeScene(int nextScn){
        Scene.nextScn = nextScn;
        currState = UNLOAD;
    }


}
