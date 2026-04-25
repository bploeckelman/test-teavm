package games.zendo.tests;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
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
        // ***FAILS***: Load built-in skin from library classpath
        //VisUI.load();
        // ***WORKS*** (with correct reflection classes): Load built-in skin from internal assets (same files as built-in skin from library)
        VisUI.load(Gdx.files.internal("skins/visui-1x-default/uiskin.json"));

        // --------------------------------------------------------------------
        // TeaVM Infinite Loop
        // - LWJGL3: GdxRuntimeException: Couldn't load dependencies of asset: does-not-exist.png
        // - GWT: GdxRuntimeException: Could not submit AsyncTask: Couldn't load image 'does-not-exist.png', file does not exist
        // - TeaVM: black screen, infinite loop, browser hangs
        var mgr = new AssetManager();
        mgr.load("does-not-exist.png", Texture.class);
        mgr.finishLoading();
        // --------------------------------------------------------------------

        setScreen(new FirstScreen());
    }
}