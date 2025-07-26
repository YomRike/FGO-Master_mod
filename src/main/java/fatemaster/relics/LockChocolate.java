package fatemaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import fatemaster.masterMod;
import fatemaster.powers.CursePower;

public class LockChocolate extends CustomRelic {
    public static final String ID = masterMod.makeId(LockChocolate.class.getSimpleName());
    private static final String IMG = "fgoMasterResources/images/relics_Master/LockChocolate.png";
    private static final String IMG_OTL = "fgoMasterResources/images/relics_Master/outline/LockChocolate.png";

    public LockChocolate() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.BOSS, LandingSound.HEAVY);
    }

    @Override
    public void atBattleStart() {
        //this.counter = 0;
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new CursePower(AbstractDungeon.player, 1), 1));
    }

    @Override
    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
