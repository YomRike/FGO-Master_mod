package fatemaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import fatemaster.masterMod;

public class MidsummerNightDream extends CustomRelic {
    public static final String ID = masterMod.makeId(MidsummerNightDream.class.getSimpleName());
    private static final String IMG = "fgoMasterResources/images/relics_Master/MidsummerNightDream.png";
    private static final String IMG_OTL = "fgoMasterResources/images/relics_Master/outline/MidsummerNightDream.png";

    public MidsummerNightDream() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.RARE, AbstractRelic.LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
