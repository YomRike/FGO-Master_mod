package fatemaster.powers.deprecated;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class TheOneWhoRevealsTruthPower extends AbstractPower {
    public static final String POWER_ID = "TheOneWhoRevealsTruthPower";
    public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
    public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;

    public TheOneWhoRevealsTruthPower(AbstractCreature owner) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        String path128 = "fgoMasterResources/images/powers_Master/PutOnFakeFacePower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/PutOnFakeFacePower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.name = NAME;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public AbstractPower makeCopy() {
        return new TheOneWhoRevealsTruthPower(this.owner);
    }
}
