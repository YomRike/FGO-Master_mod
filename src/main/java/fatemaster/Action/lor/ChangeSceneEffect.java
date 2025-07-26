package fatemaster.Action.lor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
public class ChangeSceneEffect extends AbstractChangeSceneEffect {
    private final Texture img;
    public float x;
    public Color color;
    public ChangeSceneEffect(Texture img) {
        this.color = Color.WHITE.cpy();
        this.renderBehind = true;
        this.img = img;
        this.x = -Settings.WIDTH * 1.7F;
    }

    @Override
    public void update() {}

    @Override
    public void end() {
        this.x = Settings.WIDTH - Settings.WIDTH / 0.8F;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.flush();
        sb.setColor(Color.WHITE.toFloatBits());
        sb.draw(this.img, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
    }

    @Override
    public void dispose() {}
}