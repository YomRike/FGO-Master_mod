package fatemaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import fatemaster.masterMod;

public class BlessRockChoco extends CustomRelic {
    public static final String ID = masterMod.makeId(BlessRockChoco.class.getSimpleName());
    private static final String IMG = "fgoMasterResources/images/relics_Master/BlessRockChoco.png";
    private static final String IMG_OTL = "fgoMasterResources/images/relics_Master/outline/BlessRockChoco.png";

    public BlessRockChoco() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (drawnCard.costForTurn >= 3) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            drawnCard.costForTurn -= 1;
            drawnCard.isCostModifiedForTurn = true;
        }
    }
}
