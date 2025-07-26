package fatemaster.Action;

import fatemaster.cards.colorless.FlamesofApplause;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class JewelOfFourteenAction extends AbstractGameAction {
    private final boolean upgrade;
    public JewelOfFourteenAction(boolean upgraded, int amount) {
        this.amount = amount;
        this.actionType = ActionType.WAIT;
        this.upgrade = upgraded;
    }

    @Override
    public void update() {
        int toDraw = this.amount - AbstractDungeon.player.hand.size();
        if (toDraw > 0) {
            if (this.upgrade) {
                AbstractCard s = (new FlamesofApplause()).makeCopy();
                s.upgrade();
                this.addToTop(new MakeTempCardInHandAction(s, toDraw));
            } else {
                this.addToTop(new MakeTempCardInHandAction(new FlamesofApplause(), toDraw));
            }
        }

        this.isDone = true;
    }
}
