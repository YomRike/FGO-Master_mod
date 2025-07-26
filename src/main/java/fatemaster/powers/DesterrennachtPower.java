package fatemaster.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import fatemaster.masterMod;

public class DesterrennachtPower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(DesterrennachtPower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;
    private static int desIdOffset;
    public DesterrennachtPower(AbstractCreature owner, int amount) {
        super(POWER_ID + desIdOffset, (getPowerStrings(POWER_ID)).NAME);
        ++desIdOffset;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;

        String path128 = "fgoMasterResources/images/powers_Master/EndOfADreamPower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/EndOfADreamPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[2];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        }
    }

    @Override
    public void atEndOfRound() {
        this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        if (this.amount == 1) {
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, -4), -4));
        }
    }
}
