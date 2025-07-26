package fatemaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import fatemaster.masterMod;

public class SaveStone extends CustomRelic {
    public static final String ID = masterMod.makeId(SaveStone.class.getSimpleName());
    private static final String IMG = "fgoMasterResources/images/relics_Master/SaveStone.png";
    private static final String IMG_OTL = "fgoMasterResources/images/relics_Master/outline/SaveStone.png";

    public SaveStone() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.COMMON, AbstractRelic.LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + 3.5 + this.DESCRIPTIONS[1];
    }

    @Override
    public void atBattleStart() {
        this.flash();
        AbstractDungeon.player.gainGold((int) (AbstractDungeon.player.gold * 0.035F));
        this.grayscale = true;
    }

    @Override
    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }
}
