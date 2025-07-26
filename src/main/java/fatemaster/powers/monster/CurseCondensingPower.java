package fatemaster.powers.monster;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.CursePower;
import fatemaster.powers.PowerBase;

public class CurseCondensingPower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(CurseCondensingPower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;

    public CurseCondensingPower(AbstractCreature owner, int amount) {
        super(POWER_ID, (getPowerStrings(POWER_ID)).NAME);
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;

        String path128 = "fgoMasterResources/images/powers_Master/EndOfADreamPower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/EndOfADreamPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }

    }

    @Override
    public void atEndOfRound() {
        this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        if (this.amount == 1) {
            if (this.owner.hasPower(CursePower.POWER_ID)) {
                int CurAmt = this.owner.getPower(CursePower.POWER_ID).amount;
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, CursePower.POWER_ID));
                for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    this.addToBot(new ApplyPowerAction(mo, this.owner, new CursePower(mo, CurAmt), CurAmt));
                }
            }
        }
    }
}
