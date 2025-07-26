package fatemaster.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.*;
import fatemaster.masterMod;

public class SevenBeastCrownsPower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(SevenBeastCrownsPower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;
    private static int BeastIdOffset;

    public SevenBeastCrownsPower(AbstractCreature owner, int amount) {
        super(POWER_ID + BeastIdOffset, (getPowerStrings(POWER_ID)).NAME);
        ++BeastIdOffset;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;

        String path128 = "fgoMasterResources/images/powers_Master/DracoPower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/DracoPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (this.amount <= 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
            if (this.amount == 1) {
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new ThornsPower(this.owner, 3), 3));
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new PlatedArmorPower(this.owner, 3), 3));
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new RegenPower(this.owner, 3), 3));
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new BerserkPower(this.owner, 1), 1));
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new DrawPower(this.owner, 1), 1));
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new IntangiblePlayerPower(this.owner, 1), 1));
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new ArtifactPower(this.owner, 1), 1));
            }
        }
    }
}
