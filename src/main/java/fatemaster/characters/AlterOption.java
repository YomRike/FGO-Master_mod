package fatemaster.characters;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

public class AlterOption extends AbstractCampfireOption {
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("fatemaster:AlterOption")).TEXT;
    public static boolean usedIdentify = false;

    public AlterOption() {
        this.label = TEXT[0];
        this.description = TEXT[1];
        this.img = ImageMaster.loadImage("fgoMasterResources/images/UI_Master/tune.png");
        usedIdentify = false;
    }

    @Override
    public void useOption() {
        if (this.usable) AbstractDungeon.effectList.add(new AlterOptionEffect());
    }

    @Override
    public void update() {
        float hackScale = ReflectionHacks.getPrivate(this, AbstractCampfireOption.class, "scale");
        if (this.usable && usedIdentify) {
            this.usable = false;
            //updateImage(false);
        }

        if (this.hb.hovered) {
            if (!this.hb.clickStarted) {
                ReflectionHacks.setPrivate(this, AbstractCampfireOption.class, "scale", MathHelper.scaleLerpSnap(hackScale, Settings.scale));
                ReflectionHacks.setPrivate(this, AbstractCampfireOption.class, "scale", MathHelper.scaleLerpSnap(hackScale, Settings.scale));
            } else {
                ReflectionHacks.setPrivate(this, AbstractCampfireOption.class, "scale", MathHelper.scaleLerpSnap(hackScale, 0.9F * Settings.scale));
            }
        } else {
            ReflectionHacks.setPrivate(this, AbstractCampfireOption.class, "scale", MathHelper.scaleLerpSnap(hackScale, 0.9F * Settings.scale));
        }
        super.update();
    }
}