package fatemaster.powers.monster;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import fatemaster.masterMod;
import fatemaster.powers.CursePower;
import fatemaster.powers.PowerBase;

public class CurseDecayedFleshPower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(CurseDecayedFleshPower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;

    public CurseDecayedFleshPower(AbstractCreature owner) {
        super(POWER_ID, (getPowerStrings(POWER_ID)).NAME);
        this.owner = owner;
        this.type = PowerType.BUFF;

        String path128 = "fgoMasterResources/images/powers_Master/InfiniteGrowthPower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/InfiniteGrowthPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void updateDescription() {
       this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL && this.owner.hasPower(CursePower.POWER_ID)) {
            int curseAmount = this.owner.getPower(CursePower.POWER_ID).amount;
            float damageBonus = damage * (curseAmount * 0.1F);
            return damage + damageBonus;
        }
        return damage;
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL && this.owner != null && !this.owner.isPlayer && this.owner.hasPower(CursePower.POWER_ID)) {
             int curseAmount = this.owner.getPower(CursePower.POWER_ID).amount;
            float damageBonus = damage * (curseAmount * 0.1F);
            return damage - damageBonus;
        }
        return damage;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS
                && info.type != DamageInfo.DamageType.HP_LOSS
                && info.owner != null
                && info.owner != this.owner
                && this.owner.hasPower(CursePower.POWER_ID)) {
            this.flash();
            this.addToBot(new ReducePowerAction(this.owner, this.owner, CursePower.POWER_ID, 1));
            this.addToBot(new HealAction(this.owner, this.owner, 10));
            this.addToBot(new ApplyPowerAction(info.owner, this.owner, new CursePower(info.owner, 1), 1, AbstractGameAction.AttackEffect.NONE));
            this.addToBot(new ApplyPowerAction(info.owner, this.owner, new VulnerablePower(info.owner, 1, false), 1, AbstractGameAction.AttackEffect.NONE));
        }

        return damageAmount;
    }
}
