package fatemaster.vfx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class RelicRapidFireEffect extends AbstractGameEffect {
    public static final float gravity = 0.5F * Settings.scale;
    public static final float frictionX = 0.1F * Settings.scale;
    public static final float frictionY = 0.2F * Settings.scale;
    public static int flighttime;
    private final RelicInfo letsago;
    public boolean finishedAction;

    public RelicRapidFireEffect(AbstractRelic relic, AbstractMonster target) {
        this.letsago = new RelicInfo(relic, target);
        flighttime = 15;
    }

    public RelicRapidFireEffect(AbstractRelic relic, AbstractMonster target, int flight) {
        this.letsago = new RelicInfo(relic, target);
        flighttime = flight;
    }

    public void render(SpriteBatch sb) {
        this.letsago.render(sb);
        sb.setColor(Color.WHITE);
    }

    public void update() {
        boolean finishedEffect = true;

        int wahoo = this.letsago.update();

        if (wahoo != 3) {
            finishedEffect = false;
        }

        if (wahoo == 1) {
            this.finishedAction = true;
        }

        if (finishedEffect) {
            this.isDone = true;
        }
    }


    public void dispose() {
    }

    static class RelicInfo {
        private final float bounceplane;
        private final AbstractCreature ac;
        private final AbstractRelic ar;
        private float x;
        private float y;
        private float targetX;
        private float targetY;
        private float rotation;
        private float radialvelocity;
        private float opacity;
        private int hit;
        private int frames;

        public RelicInfo(AbstractRelic ar, AbstractCreature ac) {
            this.targetX = ac.hb.cX + MathUtils.random(ac.hb.width) - ac.hb.width / 4.0F;
            this.targetY = ac.hb.cY + MathUtils.random(ac.hb.height) - ac.hb.height / 4.0F;

            this.x = AbstractDungeon.player.hb.cX;
            this.y = AbstractDungeon.player.hb.cY;

            this.ar = ar;
            this.ac = ac;

            this.hit = 0;
            this.frames = 0;

            this.bounceplane = ac.hb.y + MathUtils.random(ac.hb.height / 4.0F, ac.hb.height / 4.0F);

            this.opacity = 1.0F;

            this.rotation = 0.0F;
            this.radialvelocity = 0.0F;
        }

        public void render(SpriteBatch sb) {
            sb.setColor(1.0F, 1.0F, 1.0F, this.opacity);
            sb.draw(this.ar.img, this.x - 64.0F, this.y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale, Settings.scale, this.rotation, 0, 0, 128, 128, false, false);
        }

        public int update() {
            if (this.hit == 0) {
                this.x = AbstractDungeon.player.hb.cX + (this.targetX - AbstractDungeon.player.hb.cX) / RelicRapidFireEffect.flighttime * this.frames;
                this.y = AbstractDungeon.player.hb.cY + (this.targetY - AbstractDungeon.player.hb.cY) / RelicRapidFireEffect.flighttime * this.frames;

                if (this.frames++ == RelicRapidFireEffect.flighttime) {
                    this.frames = 0;
                    this.hit = 1;

                    this.radialvelocity = MathUtils.random(-30, 30);

                    this.targetX = (this.targetX - this.ac.hb.cX - this.ac.hb.width / 4.0F) / 4.0F;
                    this.targetY = (this.targetY - this.ac.hb.cY) / 4.0F;
                }
            } else {
                this.targetX += (this.targetX > 0.0F) ? -RelicRapidFireEffect.frictionX : RelicRapidFireEffect.frictionX;

                if (this.y + this.targetY <= this.bounceplane) {
                    this.targetY = Math.abs(this.targetY);
                    if (this.targetY > Settings.scale) {
                        this.radialvelocity = MathUtils.random(-30, 30);
                    } else {
                        this.radialvelocity = 0.0F;
                    }
                    this.hit = 2;
                } else {
                    this.targetY -= (this.targetY > 0.0F) ? RelicRapidFireEffect.frictionY : -RelicRapidFireEffect.frictionY;
                    this.targetY -= RelicRapidFireEffect.gravity;
                }
                this.x += this.targetX;
                this.y += this.targetY;
                this.rotation += this.radialvelocity;

                if (this.hit > 1) {
                    if (this.opacity <= 0.0F) {
                        this.opacity = 0.0F;
                        this.hit = 3;
                    } else {
                        this.opacity -= 0.0033333334F;
                    }
                }
            }
            return this.hit;
        }
    }
}