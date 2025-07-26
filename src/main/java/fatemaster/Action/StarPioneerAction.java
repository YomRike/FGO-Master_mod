package fatemaster.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class StarPioneerAction extends AbstractGameAction {
    private final AbstractPlayer p = AbstractDungeon.player;
    private final AbstractCard card;
    private final boolean handPile;

    public StarPioneerAction(AbstractCard card, boolean handPile) {
        this.card = card;
        this.handPile = handPile;
    }

    @Override
    public void update() {
        if (this.p.drawPile.isEmpty()) {
            this.isDone = true;
            return;
        }

        for (AbstractCard c : new ArrayList<>(this.p.drawPile.group)) {
            if (c.costForTurn <= this.card.costForTurn) {
                this.p.drawPile.moveToDiscardPile(c);
                GameActionManager.incrementDiscard(false);
                c.triggerOnManualDiscard();
            }
        }

        if (this.handPile) {
            for (AbstractCard c : new ArrayList<>(this.p.hand.group)) {
                if (c.costForTurn <= this.card.costForTurn) {
                    this.p.hand.moveToDiscardPile(c);
                    GameActionManager.incrementDiscard(false);
                    c.triggerOnManualDiscard();
                }
            }
        }

        this.isDone = true;
    }
}
