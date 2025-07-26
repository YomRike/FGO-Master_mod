package fatemaster.powers.performance;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import fatemaster.masterMod;
import fatemaster.powers.PowerBase;
import fatemaster.powers.StarGainPower;

public class QuickCardPower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(QuickCardPower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;

    public QuickCardPower(AbstractCreature owner, int amount) {
        super(POWER_ID, (getPowerStrings(POWER_ID)).NAME);
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;

        String path128 = "fgoMasterResources/images/powers_Master/QuickCardPower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/QuickCardPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + 5 * this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.POWER) {
            this.flash();
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StarGainPower(this.owner, 5 * this.amount), 5 * this.amount));
            //this.addToBot(new ApplyPowerAction(this.owner, this.owner, new CriticalDamageUpPower(this.owner, 20 * this.amount), 20* this.amount));
        }
    }
}
