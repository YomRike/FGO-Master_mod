package fatemaster.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.relics.MisoPotato;

public class ItsInevitablePower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(ItsInevitablePower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;
    private static int ItsOffset;
    private final int raise;
    private int damage;

    public ItsInevitablePower(AbstractCreature owner, int amount, int damage, int raise) {
        super(POWER_ID + ItsOffset, (getPowerStrings(POWER_ID)).NAME);
        ++ItsOffset;
        this.owner = owner;
        this.amount = amount;
        this.damage = damage;
        this.raise = raise;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;

        String path128 = "fgoMasterResources/images/powers_Master/BurningPower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/BurningPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = String.format(DESCRIPTIONS[0], this.damage);
        } else {
            this.description = String.format(DESCRIPTIONS[1], this.amount, this.damage, this.raise);
        }
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        this.addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
        if (AbstractDungeon.player.hasRelic(MisoPotato.ID)) {
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                this.addToBot(new ApplyPowerAction(mo, this.owner, new BurnDamagePower(mo, 2), 2, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
        this.damage += this.raise;
        this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        //updateDescription();
    }
}
