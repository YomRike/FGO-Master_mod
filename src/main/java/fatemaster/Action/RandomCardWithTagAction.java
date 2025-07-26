package fatemaster.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import java.util.ArrayList;
import java.util.Map;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRng;

public class RandomCardWithTagAction extends AbstractGameAction {
    public boolean upgradeCard;
    public AbstractCard.CardTags tag;
    public boolean free;

    public RandomCardWithTagAction(AbstractCard.CardTags tagToSearch) {
        this(tagToSearch, false, false);
    }

    public RandomCardWithTagAction(AbstractCard.CardTags tagToSearch, boolean free, boolean upgradeCard) {
        this.tag = tagToSearch;
        this.free = free;
        this.upgradeCard = upgradeCard;
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    @Override
    public void update() {
        ArrayList<String> tmp = new ArrayList<>();
        for (Map.Entry<String, AbstractCard> card : CardLibrary.cards.entrySet()) {
            if (card.getValue().hasTag(this.tag)) {
                tmp.add(card.getKey());
            }
        }

        AbstractCard cStudy = CardLibrary.cards.get(tmp.get(cardRng.random(0, tmp.size() - 1))).makeCopy();

        if (this.upgradeCard) {
            cStudy.upgrade();
        }

        if (this.free) {
            cStudy.freeToPlayOnce = true;
        }

        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(cStudy, true));
        this.isDone = true;
    }
}