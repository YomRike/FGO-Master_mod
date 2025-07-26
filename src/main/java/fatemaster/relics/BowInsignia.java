package fatemaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import fatemaster.masterMod;

public class BowInsignia extends CustomRelic {
    public static final String ID = masterMod.makeId(BowInsignia.class.getSimpleName());
    private static final String IMG = "fgoMasterResources/images/relics_Master/BowInsignia.png";
    private static final String IMG_OTL = "fgoMasterResources/images/relics_Master/outline/BowInsignia.png";

    public BowInsignia() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.SHOP, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
