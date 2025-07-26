package fatemaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import fatemaster.Action.StarBasketAction;
import fatemaster.masterMod;

public class SurpriseChocolate extends CustomRelic {
    public static final String ID = masterMod.makeId(SurpriseChocolate.class.getSimpleName());
    private static final String IMG = "fgoMasterResources/images/relics_Master/SurpriseChocolate.png";
    private static final String IMG_OTL = "fgoMasterResources/images/relics_Master/outline/SurpriseChocolate.png";

    public SurpriseChocolate() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    @Override
    public void atBattleStart() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new StarBasketAction());
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
