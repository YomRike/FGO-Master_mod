package fatemaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import fatemaster.cards.curse.Dumuzid;
import fatemaster.masterMod;

public class BurgerKing extends CustomRelic {
    public static final String ID = masterMod.makeId(BurgerKing.class.getSimpleName());
    private static final String IMG = "fgoMasterResources/images/relics_Master/BurgerKing.png";
    private static final String IMG_OTL = "fgoMasterResources/images/relics_Master/outline/BurgerKing.png";

    public BurgerKing() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.BOSS, AbstractRelic.LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.increaseMaxHp(25, true);
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Dumuzid(), (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
        UnlockTracker.markCardAsSeen("Dumuzid");
    }
}
