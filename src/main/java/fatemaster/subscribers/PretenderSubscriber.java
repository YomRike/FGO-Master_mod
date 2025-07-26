package fatemaster.subscribers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public interface PretenderSubscriber {
    void onPretender(AbstractCard card, AbstractPlayer p);
}
