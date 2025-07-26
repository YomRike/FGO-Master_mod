package fatemaster.powers.monster;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import fatemaster.masterMod;
import fatemaster.powers.PowerBase;

public class FaeQueenPower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(FaeQueenPower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;
    private int turns;

    public FaeQueenPower(AbstractCreature owner) {
        super(POWER_ID, (getPowerStrings(POWER_ID)).NAME);
        this.owner = owner;
        this.type = PowerType.BUFF;

        String path128 = "fgoMasterResources/images/powers_Master/DelayedBuffPower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/DelayedBuffPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (this.turns == 2) {
            this.description = DESCRIPTIONS[0] + DESCRIPTIONS[3];
        } else {
            this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1] + this.turns + DESCRIPTIONS[2];
        }
    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        this.flash();
       if (this.turns == 2) {
           this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, 2), 2));
           this.addToBot(new ApplyPowerAction(AbstractDungeon.player, this.owner, new VulnerablePower(AbstractDungeon.player, 1, true), 1));
           this.turns = 0;
       } else {
           ++turns;
       }

        for (AbstractPower power : this.owner.powers) {
            if (power.type == AbstractPower.PowerType.DEBUFF) {
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, power));
                break;
            }
        }

        updateDescription();
    }
}
