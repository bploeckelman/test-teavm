package games.zendo.tests;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.kotcrab.vis.ui.VisUI;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 720;

    @Override
    public void create() {
        // NOTE: VisUI is a TeaVM breaker, only if loaded - comment out both VisUI.load(...)'s and it works
        //  - doesn't matter if built-in (library classpath) skin or same skin loaded from internal assets
        //  - doesn't matter if TeaVMBuilder includes .addReflectionClass(...) with same
        //  - with correct reflection classes, still fails to load built-in classpath skin

//        VisUI.setSkipGdxVersionCheck(true);

        // Load built-in skin from library classpath
//        VisUI.load();
        // Load built-in skin from internal assets (same files as built-in skin from library)
        VisUI.load(Gdx.files.internal("skins/visui-1x-default/uiskin.json"));

        setScreen(new FirstScreen());
    }
}