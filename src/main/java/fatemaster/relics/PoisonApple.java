package fatemaster.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.unique.BouncingFlaskAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

public class PoisonApple extends CustomRelic {
    public static final String ID = masterMod.makeId(PoisonApple.class.getSimpleName());
    private static final String IMG = "fgoMasterResources/images/relics_Master/PoisonApple.png";
    private static final String IMG_OTL = "fgoMasterResources/images/relics_Master/outline/PoisonApple.png";

    public PoisonApple() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public int onPlayerGainedBlock(float blockAmount) {
        //this.flash();
        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        this.addToBot(new BouncingFlaskAction(randomMonster, 1, 1));
        return MathUtils.floor(blockAmount);
    }
}
