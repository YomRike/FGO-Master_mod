package fatemaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import fatemaster.masterMod;

import static com.megacrit.cardcrawl.helpers.ImageMaster.loadImage;

public class SkullCandy extends CustomRelic {
    public static final String ID = masterMod.makeId(SkullCandy.class.getSimpleName());
    private static final String IMG = "fgoMasterResources/images/relics_Master/SkullCandy.png";
    private static final String IMG_OTL = "fgoMasterResources/images/relics_Master/outline/SkullCandy.png";

    public SkullCandy() {
        super(ID, loadImage(IMG), loadImage(IMG_OTL), RelicTier.SPECIAL, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onEquip() {
        if (AbstractDungeon.player.hasRelic(HalloweenRoyalty.ID)) {
            AbstractDungeon.player.getRelic(HalloweenRoyalty.ID).onTrigger();
        }
        if (AbstractDungeon.player.hasRelic(DecisiveBattleUniform.ID)) {
            AbstractDungeon.player.getRelic(DecisiveBattleUniform.ID).onTrigger();
        }
    }
}
