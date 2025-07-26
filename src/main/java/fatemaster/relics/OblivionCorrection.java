package fatemaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import fatemaster.masterMod;
import fatemaster.powers.CriticalDamageUpPower;

public class OblivionCorrection extends CustomRelic {
    public static final String ID = masterMod.makeId(OblivionCorrection.class.getSimpleName());
    private static final String IMG = "fgoMasterResources/images/relics_Master/OblivionCorrection.png";
    private static final String IMG_OTL = "fgoMasterResources/images/relics_Master/outline/OblivionCorrection.png";

    public OblivionCorrection() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.SPECIAL, LandingSound.FLAT);
    }

    @Override
    public void atBattleStart() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new CriticalDamageUpPower(AbstractDungeon.player, 30), 30));
        this.grayscale = true;
    }

    @Override
    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
