package fatemaster.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import fatemaster.masterMod;

public class SquireOfProphecyPower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(SquireOfProphecyPower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;
    private int listAmt;

    public SquireOfProphecyPower(AbstractCreature owner, int amount) {
        super(POWER_ID, (getPowerStrings(POWER_ID)).NAME);
        this.owner = owner;
        this.amount = amount;
        this.listAmt = 1;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;

        String path128 = "fgoMasterResources/images/powers_Master/FightToDeathPower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/FightToDeathPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (EnergyPanel.totalCount == 0 && this.listAmt > 0) {
            this.flash();
            this.addToBot(new GainEnergyAction(1));
            --this.listAmt;
            /*if (this.amount == 1) {
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
            }*/
            this.updateDescription();
            //this.addToBot(new ApplyPowerAction(this.owner, this.owner, new NoPrayerForRainPower(this.owner), 1));
        }
    }

    @Override
    public void atStartOfTurn() {
        this.listAmt = 1;
        this.updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
        if (this.listAmt == 0) {
            this.description = this.description + DESCRIPTIONS[3];
        }
    }
}