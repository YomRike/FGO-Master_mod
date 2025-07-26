package fatemaster.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class TameshiMonoAction extends AbstractGameAction {
    public TameshiMonoAction() {
        this.setValues(AbstractDungeon.player, AbstractDungeon.player);
        this.actionType = ActionType.BLOCK;
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> cardsToExhaust = new ArrayList<>();
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == AbstractCard.CardType.ATTACK) {
                cardsToExhaust.add(c);
                break;
            }
        }

        for (AbstractCard cc : cardsToExhaust) {
            this.addToTop(new ExhaustSpecificCardAction(cc, AbstractDungeon.player.hand));
        }

        this.isDone = true;
    }
}
