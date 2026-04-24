package games.zendo.tests;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {

    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final Rectangle worldBounds;
    private final Sprite sprite;
    private final Vector2 pos;
    private final Vector2 vel;

    private final float minSpeed = 300f;
    private final float maxSpeed = 500f;

    private float speed;

    public FirstScreen() {
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.viewport = new FillViewport(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT, camera);
        this.worldBounds = new Rectangle();
        this.sprite = new Sprite(new Texture("libgdx.png"));
        this.pos = new Vector2();
        this.vel = new Vector2();
        this.speed = 0f;
    }

    @Override
    public void show() {
        // Prepare your screen here.
        speed = MathUtils.random(minSpeed, maxSpeed);
        vel.set(MathUtils.random(), MathUtils.random()).nor().scl(speed);
        pos.set(0, 0);

        sprite.setPosition(pos.x, pos.y);

        viewport.update(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        var halfW = viewport.getWorldWidth() / 2f;
        var halfH = viewport.getWorldHeight() / 2f;
        worldBounds.set(-halfW, -halfH, viewport.getWorldWidth(), viewport.getWorldHeight());
    }

    @Override
    public void render(float delta) {
        pos.add(vel.x * delta, vel.y * delta);

        // Bouncy bouncy
        var right = pos.x + sprite.getWidth();
        var top   = pos.y + sprite.getHeight();
        if (pos.x <= worldBounds.x || right >= worldBounds.x + worldBounds.width)  {
            pos.x = MathUtils.clamp(pos.x, worldBounds.x, worldBounds.x + worldBounds.width);
            speed = MathUtils.random(minSpeed, maxSpeed);
            vel.x *= -1;
            vel.nor().scl(speed);
        }
        if (pos.y <= worldBounds.y || top   >= worldBounds.y + worldBounds.height) {
            pos.y = MathUtils.clamp(pos.y, worldBounds.y, worldBounds.y + worldBounds.height);
            speed = MathUtils.random(minSpeed, maxSpeed);
            vel.y *= -1;
            vel.nor().scl(speed);
        }

        sprite.setPosition(pos.x, pos.y);

        ScreenUtils.clear(Color.DARK_GRAY);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

        // Resize your screen here. The parameters represent the new window size.
        viewport.update(width, height);
        var halfW = viewport.getWorldWidth() / 2f;
        var halfH = viewport.getWorldHeight() / 2f;
        worldBounds.set(-halfW, -halfH, viewport.getWorldWidth(), viewport.getWorldHeight());
        pos.x = MathUtils.clamp(pos.x, worldBounds.x, worldBounds.x + worldBounds.width);
        pos.y = MathUtils.clamp(pos.y, worldBounds.y, worldBounds.y + worldBounds.width);

    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
    }
}