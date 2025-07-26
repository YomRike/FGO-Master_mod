package fatemaster.powers.monster;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import fatemaster.masterMod;
import fatemaster.powers.PowerBase;

public class CooldownFgoPower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(CooldownFgoPower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;
    private final AbstractCard card;

    public CooldownFgoPower(AbstractCreature owner, int cardAmt, AbstractCard copyMe) {
        super(POWER_ID + copyMe.makeStatEquivalentCopy().cardID, (getPowerStrings(POWER_ID)).NAME);
        this.owner = owner;
        this.amount = cardAmt;
        this.card = copyMe.makeStatEquivalentCopy();
        this.card.resetAttributes();
        this.type = PowerType.BUFF;

        String path128 = "fgoMasterResources/images/powers_Master/PutOnFakeFacePower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/PutOnFakeFacePower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + FontHelper.colorString(this.card.name, "y") + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }

    @Override
    public void atStartOfTurnPostDraw() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        }
    }
}
