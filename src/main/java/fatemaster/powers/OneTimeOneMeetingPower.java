package fatemaster.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import fatemaster.Action.ExcaliburAction;
import fatemaster.masterMod;

public class OneTimeOneMeetingPower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(OneTimeOneMeetingPower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;

    public OneTimeOneMeetingPower(AbstractCreature owner, int amount) {
        super(POWER_ID, (getPowerStrings(POWER_ID)).NAME);
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;

        String path128 = "fgoMasterResources/images/powers_Master/CardTypeChangePower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/CardTypeChangePower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (card.type != AbstractCard.CardType.POWER && !this.owner.hasPower("No Draw")) {
            this.flash();
            //this.addToTop(new ExhaustSpecificCardAction(card, AbstractDungeon.player.hand));
            AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.POWER).makeCopy();
            //this.addToBot(new MakeTempCardInHandAction(c, true));
            this.addToTop(new ExcaliburAction(card, c));
        }

    }

    @Override
    public void atEndOfRound() {
        this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
    }
}
