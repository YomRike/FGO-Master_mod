package fatemaster.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MillenniumCastleAction extends AbstractGameAction {

    public MillenniumCastleAction(int amount) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = amount;
    }

    @Override
    public void update() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            c.baseDamage += this.amount;
            c.baseBlock += this.amount;
            c.applyPowers();
            c.flash();
        }

        this.isDone = true;
    }
}
