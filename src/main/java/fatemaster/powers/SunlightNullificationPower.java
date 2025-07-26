package fatemaster.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import fatemaster.masterMod;

public class SunlightNullificationPower extends PowerBase implements HealthBarRenderPower {
    public static String POWER_ID = masterMod.makeId(SunlightNullificationPower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;

    public SunlightNullificationPower(AbstractCreature owner, int amount) {
        super(POWER_ID, (getPowerStrings(POWER_ID)).NAME);
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;

        String path128 = "fgoMasterResources/images/powers_Master/SunlightNullificationPower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/SunlightNullificationPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            if (this.owner.hasPower(VigorPower.POWER_ID)) {
                this.flash();
                this.addToBot(new LoseHPAction(this.owner, this.owner, this.owner.getPower(VigorPower.POWER_ID).amount * this.amount, AbstractGameAction.AttackEffect.NONE));
            }
        }
    }

    @Override
    public int getHealthBarAmount() {
        if (this.owner.hasPower(VigorPower.POWER_ID)) {
            return this.owner.getPower(VigorPower.POWER_ID).amount;
        }
        return 0;
    }

    @Override
    public Color getColor() {
        return CardHelper.getColor(125, 125, 125);
    }
}
