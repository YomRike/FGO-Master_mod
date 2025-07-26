package fatemaster.cards.fgoNormal;

import fatemaster.cards.colorless.PoisonousDagger;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

public class NFFSpecial extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/NFFSpecial.png";
    public static final String ID = masterMod.makeId(NFFSpecial.class.getSimpleName());
    private static final int COST = 0;

    public NFFSpecial() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new PoisonousDagger();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(p, this.magicNumber));
        this.addToBot(new MakeTempCardInDiscardAction(this.cardsToPreview, 2));
    }
}
