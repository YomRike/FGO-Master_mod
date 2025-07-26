package fatemaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import fatemaster.Action.NP.FgoNpAction;
import fatemaster.masterMod;

import static com.megacrit.cardcrawl.helpers.ImageMaster.loadImage;

public class SuitcaseFgo extends CustomRelic {
    public static final String ID = masterMod.makeId(SuitcaseFgo.class.getSimpleName());
    private static final String IMG = "fgoMasterResources/images/relics_Master/SuitcaseFgo.png";
    private static final String IMG_OTL = "fgoMasterResources/images/relics_Master/outline/SuitcaseFgo.png";

    public SuitcaseFgo() {
        super(ID, loadImage(IMG), loadImage(IMG_OTL), RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToTop(new GainEnergyAction(1));
        this.addToBot(new FgoNpAction(20 + (10 * (AbstractDungeon.actNum - 1)), true));
        this.grayscale = true;
    }

    @Override
    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
