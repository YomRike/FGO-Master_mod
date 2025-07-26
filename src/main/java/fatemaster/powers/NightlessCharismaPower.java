package fatemaster.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import fatemaster.Action.NightlessCharismaAction;
import fatemaster.masterMod;

public class NightlessCharismaPower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(NightlessCharismaPower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;

    public NightlessCharismaPower(AbstractCreature owner, int amount) {
        super(POWER_ID, (getPowerStrings(POWER_ID)).NAME);
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;

        String path128 = "fgoMasterResources/images/powers_Master/SunlightPower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/SunlightPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        this.addToBot(new NightlessCharismaAction(this.amount, AbstractCard.CardType.ATTACK));
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }
}
