package fatemaster.powers.monster;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import fatemaster.masterMod;
import fatemaster.powers.CursePower;
import fatemaster.powers.PowerBase;

public class PowerofGrudgePower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(PowerofGrudgePower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;

    public PowerofGrudgePower(AbstractCreature owner) {
        super(POWER_ID, (getPowerStrings(POWER_ID)).NAME);
        this.owner = owner;
        this.type = PowerType.BUFF;

        String path128 = "fgoMasterResources/images/powers_Master/AttackUpPower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/AttackUpPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL && this.owner.hasPower(CursePower.POWER_ID) ? damage + this.owner.getPower(CursePower.POWER_ID).amount : damage;
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            return this.owner != null && !this.owner.isPlayer && this.owner.hasPower(CursePower.POWER_ID) ? damage + this.owner.getPower(CursePower.POWER_ID).amount : damage;
        } else {
            return damage;
        }
    }
}
