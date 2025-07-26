package fatemaster.powers.deprecated;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import fatemaster.Enum.CardTagsEnum;
import fatemaster.cards.fgoNormal.CharismaOfTheJade;
import fatemaster.masterMod;
import fatemaster.powers.CriticalDamageUpPower;
import fatemaster.powers.CrossingArcadiaPower;
import fatemaster.powers.HeroicKingPower;
import fatemaster.powers.PowerBase;

public class StarGain0Power extends PowerBase {
    public static String POWER_ID = masterMod.makeId(StarGain0Power.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;

    public StarGain0Power(AbstractCreature owner, int amount) {
        super(POWER_ID, (getPowerStrings(POWER_ID)).NAME);
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;

        String path128 = "fgoMasterResources/images/powers_Master/StarGainPower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/StarGainPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (this.amount >= 10) {
            this.description = DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0];
        }
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        //你有10颗暴击星时才能暴击。
        if (!card.hasTag(CardTagsEnum.Noble_Phantasm) && this.amount >= 10) {
            //特例：翡翠的魅力、20暴击星
            if (card.cardID.equals(CharismaOfTheJade.ID) && this.amount >= 20) {
                return this.atDamageGive2(damage, type);
            }
            return this.atDamageGive(damage, type);
        }
        return damage;
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        //暴击威力提高。
        if (this.owner.hasPower(CriticalDamageUpPower.POWER_ID)) {
            int CrAmt = (this.owner.getPower(CriticalDamageUpPower.POWER_ID)).amount;
            return type == DamageInfo.DamageType.NORMAL ? damage + 6.0F + CrAmt : damage;
        }
        return type == DamageInfo.DamageType.NORMAL ? damage + 6.0F : damage;
    }

    //翡翠的魅力暴击伤害。
    public float atDamageGive2(float damage, DamageInfo.DamageType type) {
        //暴击威力提高。
        if (this.owner.hasPower(CriticalDamageUpPower.POWER_ID)) {
            int CrAmt = (this.owner.getPower(CriticalDamageUpPower.POWER_ID)).amount;
            return type == DamageInfo.DamageType.NORMAL ? (damage + CrAmt) * 3.0F : damage;
        }
        return type == DamageInfo.DamageType.NORMAL ? damage * 3.0F : damage;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        //在你打出攻击牌时，且有10颗以上暴击星，且不是宝具牌。
        if (card.type == AbstractCard.CardType.ATTACK && this.amount >= 10) {
            if (!this.owner.hasPower(CrossingArcadiaPower.POWER_ID)) {
                if (card.cardID.equals(CharismaOfTheJade.ID) && this.amount >= 20) {
                    this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 20));
                } else {
                    this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 10));
                }
            }
        }
    }
}
