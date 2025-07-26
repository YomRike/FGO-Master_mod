package fatemaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import fatemaster.masterMod;

public class MisoPotato extends CustomRelic {
    public static final String ID = masterMod.makeId(MisoPotato.class.getSimpleName());
    private static final String IMG = "fgoMasterResources/images/relics_Master/MisoPotato.png";
    private static final String IMG_OTL = "fgoMasterResources/images/relics_Master/outline/MisoPotato.png";

    public MisoPotato() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.SHOP, AbstractRelic.LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
