package fatemaster.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import fatemaster.masterMod;

public class HugeScalePower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(HugeScalePower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;

    public HugeScalePower(AbstractCreature owner, int amount) {
        super(POWER_ID, (getPowerStrings(POWER_ID)).NAME);
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;

        String path128 = "fgoMasterResources/images/powers_Master/BuffRegenPower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/BuffRegenPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        /*AbstractDungeon.player.increaseMaxHp(this.amount, true);
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new MaxHPPower(this.owner, this.amount), this.amount));*/
        this.addToBot(new GainBlockAction(this.owner, this.owner, this.amount));
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new InfiniteGrowthPower(this.owner, this.amount), this.amount));
    }
}
