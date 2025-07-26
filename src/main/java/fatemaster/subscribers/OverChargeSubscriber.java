package fatemaster.subscribers;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public interface OverChargeSubscriber {
    void onOverCharge(AbstractPlayer p, AbstractMonster m);
}
