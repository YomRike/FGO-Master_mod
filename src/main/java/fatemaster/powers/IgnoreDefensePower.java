package fatemaster.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import fatemaster.masterMod;

public class IgnoreDefensePower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(IgnoreDefensePower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;

    public IgnoreDefensePower(AbstractCreature owner, int amount) {
        super(POWER_ID, (getPowerStrings(POWER_ID)).NAME);
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;

        String path128 = "fgoMasterResources/images/powers_Master/IgnoreDefensePower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/IgnoreDefensePower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.currentBlock > 0) {
                this.addToBot(new LoseHPAction(mo, this.owner, mo.currentBlock, AbstractGameAction.AttackEffect.SHIELD));
                this.addToBot(new ApplyPowerAction(mo, this.owner, new VulnerablePower(mo, this.amount, false), this.amount, AbstractGameAction.AttackEffect.NONE));
                this.addToBot(new RemoveAllBlockAction(mo, this.owner));
            }
        }
    }
}
