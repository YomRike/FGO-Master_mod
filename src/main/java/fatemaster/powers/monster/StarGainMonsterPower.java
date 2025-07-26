package fatemaster.powers.monster;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import fatemaster.masterMod;
import fatemaster.powers.CriticalDamageUpPower;
import fatemaster.powers.PowerBase;

public class StarGainMonsterPower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(StarGainMonsterPower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;

    public StarGainMonsterPower(AbstractCreature owner, int amount) {
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
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (this.amount >= 10) {
            return this.atDamageGive(damage, type);
        }
        return damage;
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (this.owner.hasPower(CriticalDamageUpPower.POWER_ID)) {
            int CrAmt = (this.owner.getPower(CriticalDamageUpPower.POWER_ID)).amount;
            return type == DamageInfo.DamageType.NORMAL ? damage * (200.0F + CrAmt) / 100.0F : damage;
        }
        return type == DamageInfo.DamageType.NORMAL ? damage * 2.0F : damage;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        this.flash();
        this.addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 10));
    }
}
