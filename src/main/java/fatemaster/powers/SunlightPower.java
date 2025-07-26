package fatemaster.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import fatemaster.Action.SunlightAction;
import fatemaster.masterMod;

public class SunlightPower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(SunlightPower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;

    public SunlightPower(AbstractCreature owner) {
        super(POWER_ID, (getPowerStrings(POWER_ID)).NAME);
        this.owner = owner;
        this.type = PowerType.BUFF;

        String path128 = "fgoMasterResources/images/powers_Master/SunlightPower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/SunlightPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    /*@Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            if (this.owner.hasPower(VigorPower.POWER_ID)) {
                int VigorAmt = this.owner.getPower(VigorPower.POWER_ID).amount;
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new VigorPower(this.owner, VigorAmt), VigorAmt));
            }
        }
    }*/

    @Override
    public void atStartOfTurn() {
        this.flash();
        this.addToBot(new SunlightAction());
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
