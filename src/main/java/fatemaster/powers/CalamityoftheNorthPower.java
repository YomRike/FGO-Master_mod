package fatemaster.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import fatemaster.masterMod;

public class CalamityoftheNorthPower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(CalamityoftheNorthPower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;
    private int damage;

    public CalamityoftheNorthPower(AbstractCreature owner, int damage) {
        super(POWER_ID, (getPowerStrings(POWER_ID)).NAME);
        this.owner = owner;
        this.amount = 9;
        this.damage = damage;
        this.type = PowerType.BUFF;

        String path128 = "fgoMasterResources/images/powers_Master/CalamityoftheNorthPower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/CalamityoftheNorthPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.damage + DESCRIPTIONS[2];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[3] + this.damage + DESCRIPTIONS[2];
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.damage += stackAmount;
        this.updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        --this.amount;
        if (this.amount == 0) {
            this.flash();
            this.amount = 9;
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!mo.isDeadOrEscaped()) {
                    this.addToBot(new ApplyPowerAction(mo, this.owner, new PoisonPower(mo, this.owner, this.damage), this.damage));
                    this.addToBot(new ApplyPowerAction(mo, this.owner, new CursePower(mo, this.damage), this.damage));
                }
            }
        }

        this.updateDescription();
    }
}
