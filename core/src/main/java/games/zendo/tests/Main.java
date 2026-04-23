package games.zendo.tests;

import com.badlogic.gdx.Game;
import com.kotcrab.vis.ui.VisUI;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 720;

    @Override
    public void create() {
        VisUI.setSkipGdxVersionCheck(true);
        VisUI.load();

        setScreen(new FirstScreen());
    }
}