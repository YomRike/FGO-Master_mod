package fatemaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import fatemaster.masterMod;
import fatemaster.powers.ArchetypeORTPower;

public class ArchetypeORT extends CustomRelic {
    public static final String ID = masterMod.makeId(ArchetypeORT.class.getSimpleName());
    private static final String IMG = "fgoMasterResources/images/relics_Master/ArchetypeORT.png";
    private static final String IMG_OTL = "fgoMasterResources/images/relics_Master/outline/ArchetypeORT.png";

    public ArchetypeORT() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public void atBattleStart() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ArchetypeORTPower(AbstractDungeon.player)));
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.actNum >= 2;
    }
}
