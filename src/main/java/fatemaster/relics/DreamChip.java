package fatemaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.green.CalculatedGamble;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import fatemaster.masterMod;

public class DreamChip extends CustomRelic {
    public static final String ID = masterMod.makeId(DreamChip.class.getSimpleName());
    private static final String IMG = "fgoMasterResources/images/relics_Master/DreamChip.png";
    private static final String IMG_OTL = "fgoMasterResources/images/relics_Master/outline/DreamChip.png";
    private static boolean usedThisCombat = false;

    public DreamChip() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.SHOP, AbstractRelic.LandingSound.FLAT);
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
    public void onShuffle() {
        if (!usedThisCombat) {
            this.flash();
            this.pulse = false;
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new MakeTempCardInHandAction(new CalculatedGamble(), 1));
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
