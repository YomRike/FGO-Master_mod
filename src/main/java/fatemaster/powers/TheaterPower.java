package fatemaster.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import fatemaster.masterMod;

public class TheaterPower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(TheaterPower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;
    private int time;

    public TheaterPower(AbstractCreature owner, int amount, int time) {
        super(POWER_ID, (getPowerStrings(POWER_ID)).NAME);
        this.owner = owner;
        this.amount = amount;
        this.time = time;
        this.type = PowerType.BUFF;

        String path128 = "fgoMasterResources/images/powers_Master/GutsPower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/GutsPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void onSpecificTrigger() {
        this.addToBot(new HealAction(this.owner, this.owner, this.amount));
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new NPDamagePower(this.owner, 20), 20));
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new VigorPower(this.owner, 8), 8));
        //this.addToBot(new RemoveDebuffsAction(this.owner));
        --this.time;
        if (this.time == 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        if (this.time == 1) {
            this.description = String.format(DESCRIPTIONS[0], this.amount);
        } else {
            this.description = String.format(DESCRIPTIONS[1], this.amount, this.time);
        }
    }
}
