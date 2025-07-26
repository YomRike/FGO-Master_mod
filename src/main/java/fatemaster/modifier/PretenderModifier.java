package fatemaster.modifier;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import fatemaster.Action.ExcaliburAction;
import fatemaster.masterMod;
import fatemaster.subscribers.PretenderSubscriber;

public class PretenderModifier extends AbstractCardModifier {
    public static String ID = masterMod.makeId("PretenderModifier");

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new PretenderModifier();
    }

    @Override
    public void atEndOfTurn(AbstractCard card, CardGroup group) {
        if (group.type == CardGroup.CardGroupType.HAND) {
            if (card instanceof PretenderSubscriber)
                ((PretenderSubscriber) card).onPretender(card, AbstractDungeon.player);
            AbstractCard cm = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
            //cm.updateCost(1);
            this.addToTop(new ExcaliburAction(card, cm));
        }
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }
}
