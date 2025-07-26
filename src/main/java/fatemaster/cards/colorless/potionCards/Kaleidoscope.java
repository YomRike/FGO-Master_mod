package fatemaster.cards.colorless.potionCards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.Action.NP.FgoNpAction;
import fatemaster.cards.fgoMasterBase;
import fatemaster.masterMod;

public class Kaleidoscope extends fgoMasterBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/Kaleidoscope.png";
    public static final String ID = masterMod.makeId(Kaleidoscope.class.getSimpleName());
    private static final int COST = -2;

    public Kaleidoscope() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseMagicNumber = 80;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(20);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void onChoseThisOption() {
        this.addToBot(new FgoNpAction(this.magicNumber));
    }
}
