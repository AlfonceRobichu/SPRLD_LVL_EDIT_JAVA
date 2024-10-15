package src;


public class Screen {
    //public static int frameHeight = 720;
    //public static int frameWidth = 1280;
    public static int width = 1280;
    public static int height = 720;
    public static int centerHeight = height>>1;
    public static int centerWidth = width>>1;


    private static final int winscreenWidth = 1280;
    private static final int winscreenHeight = 720;
    public static final int fullscreenWidth = 1920;
    public static final int fullscreenHeight = 1080;

    private static boolean isFullscreenOn = false;

    public static void switchWidescreen(){
        int key = RayClass.rlj.core.GetKeyPressed();
        if (key != 300) return;     //si F11 appuyé

        if(isFullscreenOn){             //passage en mode fenêtré
            RayClass.rlj.core.ToggleFullscreen();
            height = winscreenHeight;
            width  = winscreenWidth;
            centerHeight = height>> 1;
            centerWidth  = width >> 1;
            RayClass.rlj.core.SetWindowSize(width, height);
            RayClass.rlj.core.SetWindowPosition(centerWidth>>1, centerHeight>>1);
        }
        else{                           //passage en mode plein écran
            height = fullscreenHeight;
            width  = fullscreenWidth;
            centerHeight = height>> 1;
            centerWidth  = width >> 1;
            RayClass.rlj.core.SetWindowSize(width, height);
            RayClass.rlj.core.ToggleFullscreen();
        }   

        isFullscreenOn = !isFullscreenOn;
        CamPlus.updOffsetFullscreenSwitch();

    }

}
