package fatemaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import fatemaster.Action.NP.FgoNpAction;
import fatemaster.masterMod;

public class AstrologicalTeapot extends CustomRelic {
    public static final String ID = masterMod.makeId(AstrologicalTeapot.class.getSimpleName());
    private static final String IMG = "fgoMasterResources/images/relics_Master/AstrologicalTeapot.png";
    private static final String IMG_OTL = "fgoMasterResources/images/relics_Master/outline/AstrologicalTeapot.png";

    public AstrologicalTeapot() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(SuitcaseFgo.ID);
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(SuitcaseFgo.ID)) {
            this.instantObtain(AbstractDungeon.player, AbstractDungeon.player.relics.indexOf(AbstractDungeon.player.getRelic(SuitcaseFgo.ID)), false);
        } else {
            super.obtain();
        }
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void atTurnStart() {
        if (!this.grayscale) {
            ++this.counter;
        }

        if (this.counter == 2) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToTop(new GainEnergyAction(2));
            this.addToBot(new FgoNpAction(80));
            this.counter = -1;
            this.grayscale = true;
        }

    }

    @Override
    public void onVictory() {
        this.counter = -1;
        this.grayscale = false;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
