package game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import game.utils.Resources;
import game.utils.Skins;

public abstract class AbstractScreen extends ScreenAdapter {
    private static final Skin SKIN = Skins.COMODORE_SKIN;
    private static final Texture BACKGROUND_TEXTURE = new Texture(Gdx.files.internal(Resources.GAME_IMAGES_PATH + "background.jpg"));
    private static final SpriteDrawable BACKGROUND = new SpriteDrawable(new Sprite(BACKGROUND_TEXTURE));
    private final Stage stage;
    private Table mainTable;

    public AbstractScreen() {
        stage = new Stage() {
            @Override
            public boolean keyUp(int keyCode) {
                keyPressed(keyCode);
                return super.keyUp(keyCode);
            }
        };
        createUI();
    }

    protected abstract void keyPressed(int keyCode);

    public Stage getStage() {
        return stage;
    }

    public Table getMainTable() {
        return mainTable;
    }

    public Skin getSkin() {
        return SKIN;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(getStage());
    }

    @Override
    public void render(float delta) {
        getStage().act(delta);
        getStage().draw();
    }

    private void createUI() {
        mainTable = new Table(Skins.COMODORE_SKIN);
        mainTable.setFillParent(true);
        mainTable.setBackground(BACKGROUND);
        createContent();
        getStage().addActor(getMainTable());
    }

    @Override
    public void resize(int width, int height) {
        getStage().getViewport().update(width, height);
    }

    abstract void createContent();

    @Override
    public void dispose() {
        super.dispose();
        getStage().dispose();
    }
}
