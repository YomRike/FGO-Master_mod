package fatemaster.powers.performance;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import fatemaster.masterMod;
import fatemaster.powers.PowerBase;

public class BusterCardPower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(BusterCardPower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;

    public BusterCardPower(AbstractCreature owner, int amount) {
        super(POWER_ID, (getPowerStrings(POWER_ID)).NAME);
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;

        String path128 = "fgoMasterResources/images/powers_Master/BusterCardPower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/BusterCardPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }

    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            return type == DamageInfo.DamageType.NORMAL ? damage + this.amount : damage;
        }

        return damage;
    }

    @Override
    public float modifyBlock(float blockAmount, AbstractCard card) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            return this.modifyBlock(blockAmount);
        }
        return blockAmount;
    }

    @Override
    public float modifyBlock(float blockAmount) {
        return blockAmount + this.amount;
    }
}
