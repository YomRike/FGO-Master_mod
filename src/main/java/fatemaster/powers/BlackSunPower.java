package fatemaster.powers;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import fatemaster.masterMod;
import fatemaster.modifier.BlackSunModifier;

public class BlackSunPower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(BlackSunPower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;

    public BlackSunPower(AbstractCreature owner, int amount) {
        super(POWER_ID, (getPowerStrings(POWER_ID)).NAME);
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;

        String path128 = "fgoMasterResources/images/powers_Master/BlackSunPower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/BlackSunPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (!this.owner.hasPower("No Draw")) {
            this.flash();
            if (CardModifierManager.hasModifier(card, BlackSunModifier.MODIFIER_ID)) {
                ((BlackSunModifier) CardModifierManager.getModifiers(card, BlackSunModifier.MODIFIER_ID).get(0)).value += this.amount;
                card.initializeDescription();
            } else {
                CardModifierManager.addModifier(card, new BlackSunModifier(this.amount));
            }
        }
    }
}
