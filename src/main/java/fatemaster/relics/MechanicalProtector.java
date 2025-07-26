package fatemaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import fatemaster.masterMod;

public class MechanicalProtector extends CustomRelic {
    public static final String ID = masterMod.makeId(MechanicalProtector.class.getSimpleName());
    private static final String IMG = "fgoMasterResources/images/relics_Master/MechanicalProtector.png";
    private static final String IMG_OTL = "fgoMasterResources/images/relics_Master/outline/MechanicalProtector.png";

    public MechanicalProtector() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.UNCOMMON, LandingSound.FLAT);
        this.counter = 30;
    }

    @Override
    public int onLoseHpLast(int damageAmount) {
        if (this.counter > 0) {
            if (damageAmount > this.counter) {
                this.flash();
                this.setCounter(0);
                return damageAmount - this.counter;
            } else if (damageAmount > 0) {
                this.flash();
                this.counter -= damageAmount;
                return 0;
            }
        }

        return damageAmount;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void setCounter(int setCounter) {
        this.counter = setCounter;
        if (setCounter <= 0) {
            this.usedUp();
        }
    }
}
