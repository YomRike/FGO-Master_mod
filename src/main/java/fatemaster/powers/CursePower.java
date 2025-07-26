package fatemaster.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import fatemaster.masterMod;
import fatemaster.powers.deprecated.AvalonPower;

public class CursePower extends PowerBase implements HealthBarRenderPower {
    public static String POWER_ID = masterMod.makeId(CursePower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;

    public CursePower(AbstractCreature owner, int amount) {
        super(POWER_ID, (getPowerStrings(POWER_ID)).NAME);
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;

        String path128 = "fgoMasterResources/images/powers_Master/CursePower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/CursePower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        if (this.owner.hasPower(AvalonPower.POWER_ID)) {
            this.addToBot(new HealAction(this.owner, this.owner, this.amount));
            this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        } else {
            this.addToBot(new LoseHPAction(this.owner, this.owner, this.amount));
        }
    }

    @Override
    public int getHealthBarAmount() {
        return this.amount;
    }

    @Override
    public Color getColor() {
        return CardHelper.getColor(232, 127, 223);
    }
}
