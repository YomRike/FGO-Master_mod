package fatemaster.Button;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import fatemaster.Action.NP.NoblePhantasmSelectAction;
import fatemaster.characters.master;
import fatemaster.relics.BowInsignia;

public class NoblePhantasmButton {
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString("NoblePhantasmButton");
    private static final Texture NP_MAX = ImageMaster.loadImage("fgoMasterResources/images/UI_Master/np max.png");
    private final Vector2 position = new Vector2();
    private final Color renderColor;
    public Hitbox hb;

    public NoblePhantasmButton(float x, float y) {
        this.hb = new Hitbox(64.0F * Settings.scale, 64.0F * Settings.scale);
        this.position.x = x;
        this.position.y = y;
        this.hb.move(x, y);
        this.renderColor = Color.WHITE.cpy();
    }

    public void update() {
        this.hb.update();
        this.hb.move(this.position.x, this.position.y);
        if (this.hb.hovered) {
            if (InputHelper.justClickedLeft && master.fgoNp >= 100) {
                InputHelper.justClickedLeft = false;

                if (AbstractDungeon.player.hasRelic(BowInsignia.ID)) {
                    AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 3));
                } else {
                    if (master.fgoNp == 300) {
                        AbstractDungeon.actionManager.addToBottom(new NoblePhantasmSelectAction(true, 2));
                    } else if (master.fgoNp >= 200) {
                        AbstractDungeon.actionManager.addToBottom(new NoblePhantasmSelectAction(true, 1));
                    } else {
                        AbstractDungeon.actionManager.addToBottom(new NoblePhantasmSelectAction(false, 1));
                    }
                }

                master.fgoNp = 0;
                ((master) AbstractDungeon.player).TruthValueUpdatedEvent();
                CardCrawlGame.sound.playA("UI_CLICK_1", -0.1F);
            }
            if (this.hb.justHovered) {
                CardCrawlGame.sound.playA("UI_HOVER", -0.4F);
            }
            if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT && master.fgoNp >= 100) {
                TipHelper.renderGenericTip(
                        AbstractDungeon.player.hb.cX + AbstractDungeon.player.hb.width / 2.0F + 20.0F * Settings.scale,
                        this.hb.y + this.hb.cY + 64.0F * Settings.scale,
                        orbString.NAME, AbstractDungeon.player.hasRelic(BowInsignia.ID) ?
                                orbString.DESCRIPTION[3] : orbString.DESCRIPTION[0] + orbString.DESCRIPTION[1] + orbString.DESCRIPTION[2]);
            }
            this.renderColor.r = 1.0F;
            this.renderColor.g = 1.0F;
            this.renderColor.b = 1.0F;
        } else {
            this.renderColor.r = 0.8F;
            this.renderColor.g = 0.8F;
            this.renderColor.b = 0.8F;
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.renderColor);
        if (master.fgoNp >= 100 && !AbstractDungeon.isScreenUp) {
            sb.draw(NP_MAX, this.hb.cX - 32.0F * Settings.scale, this.hb.cY - 32.0F * Settings.scale, 48.0F, 48.0F);
            //FontHelper.renderFontCentered(sb, FontHelper.healthInfoFont, "√", this.hb.cX, this.hb.cY, this.renderColor);
        }
        this.hb.render(sb);
        if (this.hb.hovered) {
            CardCrawlGame.cursor.render(sb);
        }
    }
}