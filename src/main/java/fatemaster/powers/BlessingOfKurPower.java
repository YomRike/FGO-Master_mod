package fatemaster.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import fatemaster.Enum.CardTagsEnum;
import fatemaster.cards.noblecards.KurKigalIrkalla;
import fatemaster.masterMod;

public class BlessingOfKurPower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(BlessingOfKurPower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;

    public BlessingOfKurPower(AbstractCreature owner, int amount) {
        super(POWER_ID, (getPowerStrings(POWER_ID)).NAME);
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;

        String path128 = "fgoMasterResources/images/powers_Master/BlessingOfKurPower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/BlessingOfKurPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(CardTagsEnum.Noble_Phantasm)) {
            this.flash();
            if (card.cardID.equals(KurKigalIrkalla.ID)) {
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new MaxHPPower(this.owner, this.amount * 2), this.amount * 2));
            } else {
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new MaxHPPower(this.owner, this.amount), this.amount));
            }
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
    }
}
