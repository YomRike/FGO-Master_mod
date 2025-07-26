package fatemaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import fatemaster.masterMod;
import fatemaster.powers.deprecated.AvalonPower;

import static com.megacrit.cardcrawl.helpers.ImageMaster.loadImage;

public class LockChocolateStrawberry extends CustomRelic {
    public static final String ID = masterMod.makeId(LockChocolateStrawberry.class.getSimpleName());
    private static final String IMG = "fgoMasterResources/images/relics_Master/LockChocolateStrawberry.png";
    private static final String IMG_OTL = "fgoMasterResources/images/relics_Master/outline/LockChocolateStrawberry.png";

    public LockChocolateStrawberry() {
        super(ID, loadImage(IMG), loadImage(IMG_OTL), RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AvalonPower(AbstractDungeon.player, 1), 1));
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
