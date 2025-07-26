package fatemaster.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import fatemaster.Action.MillenniumCastleAction;
import fatemaster.masterMod;

import java.util.ArrayList;

public class MillenniumCastlePower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(MillenniumCastlePower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;

    public MillenniumCastlePower(AbstractCreature owner, int amount) {
        super(POWER_ID, (getPowerStrings(POWER_ID)).NAME);
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;

        String path128 = "fgoMasterResources/images/powers_Master/MillenniumCastlePower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/MillenniumCastlePower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        /*this.addToTop(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, -1), -1));
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            this.addToTop(new ApplyPowerAction(m, m, new StrengthPower(m, -2), -2));
        }
        this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));*/

        this.addToBot(new MillenniumCastleAction(this.amount));
    }

   /* @Override
    public void onRemove() {
        if (this.owner.hasPower("Strength") && (this.owner.getPower("Strength")).amount < 0) {
            int StrAmt = 0;
            StrAmt -= (this.owner.getPower("Strength")).amount;
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, StrAmt * 2), StrAmt * 2));
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, StrAmt), StrAmt));
        }
    }*/
}
