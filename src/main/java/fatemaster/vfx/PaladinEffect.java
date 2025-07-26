package fatemaster.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

public class PaladinEffect extends AbstractGameEffect {
    private static TextureAtlas.AtlasRegion img;
    private final float x;
    private final float y;
    private boolean playedSfx = false;

    public PaladinEffect(float x, float y) {
        if (img == null) {
            img = ImageMaster.vfxAtlas.findRegion("combat/laserThick");
        }

        this.x = x;
        this.y = y;
        this.color = Color.SKY.cpy();
        this.duration = 1.0F;
        this.startingDuration = 1.0F;
    }

    @Override
    public void update() {
        if (!this.playedSfx) {
            AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(Color.SKY));
            this.playedSfx = true;
            CardCrawlGame.sound.play("ATTACK_MAGIC_BEAM_SHORT");
            CardCrawlGame.screenShake.rumble(2.0F);
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration > this.startingDuration / 2.0F) {
            this.color.a = Interpolation.pow2In.apply(1.0F, 0.0F, this.duration - 0.5F);
        } else {
            this.color.a = Interpolation.pow2Out.apply(0.0F, 1.0F, this.duration);
        }

        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);

        Color[] rainbowColors = new Color[]{
                new Color(0.0f, 0.0f, 0.4f, this.color.a * 0.7f),
                new Color(0.0f, 0.0f, 0.6f, this.color.a * 0.7f),
                new Color(0.0f, 0.0f, 0.8f, this.color.a * 0.7f),
                new Color(0.1f, 0.1f, 0.9f, this.color.a * 0.7f),
                new Color(0.2f, 0.2f, 1.0f, this.color.a * 0.7f),
                new Color(0.3f, 0.3f, 1.0f, this.color.a * 0.7f)
        };

        float rotationSpeed = 100.0f;
        float time = (this.startingDuration - this.duration) * rotationSpeed;

        sb.setColor(Color.WHITE.cpy().mul(this.color));
        sb.draw(
                img,
                this.x,
                this.y - (float) (img.packedHeight / 2),
                0.0F,
                (float) img.packedHeight / 2.0F,
                (float) img.packedWidth,
                (float) img.packedHeight,
                this.scale * 2.0F,
                this.scale * 1.5F,
                MathUtils.random(-2.0F, 2.0F)
        );

        for (int i = 0; i < rainbowColors.length; i++) {
            float offsetX = (float) Math.sin(time + i * 0.5f) * 15.0f;
            float offsetY = (float) Math.cos(time + i * 0.5f) * 15.0f;

            sb.setColor(rainbowColors[i]);
            sb.draw(
                    img,
                    this.x + offsetX,
                    this.y - (float) (img.packedHeight / 2) + offsetY,
                    0.0F,
                    (float) img.packedHeight / 2.0F,
                    (float) img.packedWidth,
                    (float) img.packedHeight,
                    this.scale * 1.5F,
                    this.scale * 0.8F,
                    MathUtils.random(-5.0F, 5.0F)
            );
        }

        sb.setBlendFunction(770, 771);
    }

    @Override
    public void dispose() {
    }
}
