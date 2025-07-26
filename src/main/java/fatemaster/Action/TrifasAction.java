package fatemaster.Action;

import basemod.cardmods.ExhaustMod;
import basemod.cardmods.RetainMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import fatemaster.masterMod;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;

public class TrifasAction extends AbstractGameAction {
    public static final String ID = masterMod.makeId("TrifasAction");
    public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    public static final String[] TEXT = uiStrings.TEXT;
    private final AbstractPlayer p;
    private static final float DURATION = Settings.ACTION_DUR_XFAST;

    public TrifasAction(AbstractCreature target, AbstractCreature source) {
        this.setValues(target, source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = DURATION;
        this.p = (AbstractPlayer)target;
    }

    @Override
    public void update() {
        if (this.duration == DURATION) {
            if (this.p.hand.isEmpty()) {
                this.isDone = true;
            } else if (this.p.hand.size() == 1) {
                AbstractCard card = (cardRandomRng.randomBoolean() ?
                        AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.ATTACK) :
                        AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.SKILL))
                        .makeCopy();
                CardModifierManager.addModifier(card, new RetainMod());
                CardModifierManager.addModifier(card, new ExhaustMod());
                this.addToTop(new ExcaliburAction(this.p.hand.getBottomCard(), card));
                this.isDone = true;
            } else {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false);
                this.tickDuration();
            }
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                AbstractCard tmpCard = AbstractDungeon.handCardSelectScreen.selectedCards.getBottomCard();
                AbstractCard card = (cardRandomRng.randomBoolean() ?
                        AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.ATTACK) :
                        AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.SKILL))
                        .makeCopy();
                CardModifierManager.addModifier(card, new RetainMod());
                CardModifierManager.addModifier(card, new ExhaustMod());
                this.addToTop(new ExcaliburAction(tmpCard, card));
                AbstractDungeon.player.hand.addToHand(tmpCard);
                AbstractDungeon.handCardSelectScreen.selectedCards.clear();
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            }

            this.tickDuration();
        }
    }
}
