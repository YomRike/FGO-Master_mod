package fatemaster.cards.curse;

import fatemaster.cards.fgoMasterBase;
import com.megacrit.cardcrawl.actions.common.SetDontTriggerAction;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

public class Dumuzid extends fgoMasterBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/Dumuzid.png";
    public static final String ID = masterMod.makeId(Dumuzid.class.getSimpleName());
    private static final int COST = -2;

    public Dumuzid() {
        super(ID, IMG_PATH, COST, CardType.CURSE, CardColor.CURSE, CardRarity.CURSE, CardTarget.NONE);
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.dontTriggerOnUseCard) {
            CardCrawlGame.sound.playV("DUMUZID", 1.0F);
        }
    }

    @Override
    public void triggerWhenDrawn() {
        this.addToBot(new SetDontTriggerAction(this, false));
    }

    @Override
    public void triggerOnEndOfTurnForPlayingCard() {
        this.dontTriggerOnUseCard = true;
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, true));
    }
}
