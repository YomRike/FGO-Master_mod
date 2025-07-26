package fatemaster.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.PhantasmalPower;
import fatemaster.masterMod;

public class DeathOfDeathPower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(DeathOfDeathPower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;

    public DeathOfDeathPower(AbstractCreature owner, int amount) {
        super(POWER_ID, (getPowerStrings(POWER_ID)).NAME);
        this.owner = owner;
        this.amount = amount;

        String path128 = "fgoMasterResources/images/powers_Master/GutsTriggerPower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/GutsTriggerPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    @Override
    public void onSpecificTrigger() {
        this.addToBot(new HealAction(this.owner, this.owner, this.amount));
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new PhantasmalPower(this.owner, 1), 1));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }

   /* @Override
    public void onRemove() {
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, -this.amount), -this.amount));
    }*/

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }
}
