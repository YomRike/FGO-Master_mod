package fatemaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import fatemaster.Action.NP.FgoNpAction;
import fatemaster.masterMod;

public class SelfReplenishmentMagic extends CustomRelic {
    public static final String ID = masterMod.makeId(SelfReplenishmentMagic.class.getSimpleName());
    private static final String IMG = "fgoMasterResources/images/relics_Master/SelfReplenishmentMagic.png";
    private static final String IMG_OTL = "fgoMasterResources/images/relics_Master/PassiveSkills.png";

    public SelfReplenishmentMagic() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.SPECIAL, LandingSound.FLAT);
    }

    @Override
    public void atTurnStart() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new FgoNpAction(6));
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
