package fatemaster.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class KingToHandAction extends AbstractGameAction {
    private final AbstractCard card;

    public KingToHandAction(AbstractCard card, int amount) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.card = card;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.discardPile.contains(this.card) && AbstractDungeon.player.hand.size() < 10) {
                AbstractDungeon.player.hand.addToHand(this.card);
                this.card.baseMagicNumber += this.amount;
                this.card.magicNumber = this.card.baseMagicNumber;
                this.card.unhover();
                this.card.setAngle(0.0F, true);
                this.card.lighten(false);
                this.card.drawScale = 0.12F;
                this.card.targetDrawScale = 0.75F;
                this.card.applyPowers();
                AbstractDungeon.player.discardPile.removeCard(this.card);
            }

            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.player.hand.glowCheck();
        }

        this.tickDuration();
        this.isDone = true;
    }
}
