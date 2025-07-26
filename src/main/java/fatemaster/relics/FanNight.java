package fatemaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import fatemaster.masterMod;

public class FanNight extends CustomRelic {
    public static final String ID = masterMod.makeId(FanNight.class.getSimpleName());
    private static final String IMG = "fgoMasterResources/images/relics_Master/FanNight.png";
    private static final String IMG_OTL = "fgoMasterResources/images/relics_Master/outline/FanNight.png";
    private static boolean usedThisCombat = false;

    public FanNight() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.RARE, AbstractRelic.LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void atPreBattle() {
        usedThisCombat = false;
        this.pulse = true;
        this.beginPulse();
    }

    @Override
    public void onBlockBroken(AbstractCreature m) {
        if (!usedThisCombat) {
            this.flash();
            this.pulse = false;
            this.addToBot(new RelicAboveCreatureAction(m, this));
            AbstractDungeon.player.heal((int) (AbstractDungeon.player.maxHealth * 0.2));
            usedThisCombat = true;
            this.grayscale = true;
        }
    }

    @Override
    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }

    @Override
    public void onVictory() {
        this.pulse = false;
    }
}
