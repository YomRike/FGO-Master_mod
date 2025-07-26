package fatemaster.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import fatemaster.masterMod;

public class PutOnFakeFacePower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(PutOnFakeFacePower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;
    private int turnCounter = 0;

    public PutOnFakeFacePower(AbstractCreature owner) {
        super(POWER_ID, (getPowerStrings(POWER_ID)).NAME);
        this.owner = owner;
        this.type = PowerType.BUFF;

        String path128 = "fgoMasterResources/images/powers_Master/PutOnFakeFacePower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/PutOnFakeFacePower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        int effectIndex = turnCounter % 3;
        switch (effectIndex) {
            case 0:
                this.addToBot(new GainEnergyAction(1));
                break;
            case 1:
                this.addToBot(new HealAction(this.owner, this.owner, 5));
                break;
            case 2:
                for (AbstractPower power : this.owner.powers) {
                    if (power.type == AbstractPower.PowerType.DEBUFF) {
                        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, power));
                        break;
                    }
                }
                break;
        }
        turnCounter++;
    }
}
