package fatemaster.cards.fgoLibrary;

import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;

public class CircuitConnect extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/CircuitConnect.png";
    public static final String ID = masterMod.makeId(CircuitConnect.class.getSimpleName());
    private static final int COST = 1;

    public CircuitConnect() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
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
        this.addToBot(new DrawPileToHandAction(this.magicNumber, CardType.ATTACK));
        this.addToBot(new DrawPileToHandAction(this.magicNumber, CardType.SKILL));
        this.addToBot(new DrawPileToHandAction(this.magicNumber, CardType.POWER));
    }
}
