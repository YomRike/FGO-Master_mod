package fatemaster.cards.fgoNormal;

import fatemaster.Action.TheOneWhoSawItAllAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.cards.fgoNormalCard;
import fatemaster.masterMod;

public class TheOneWhoSawItAll extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/TheOneWhoSawItAll.png";
    public static final String ID = masterMod.makeId(TheOneWhoSawItAll.class.getSimpleName());
    private static final int COST = 2;

    public TheOneWhoSawItAll() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
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
        this.addToBot(new TheOneWhoSawItAllAction(this.magicNumber));
    }
}
