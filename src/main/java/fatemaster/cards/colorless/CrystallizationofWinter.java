package fatemaster.cards.colorless;

import fatemaster.cards.fgoMasterBase;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.performance.BusterPerformancePower;

public class CrystallizationofWinter extends fgoMasterBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/ConjureIce.png";
    public static final String ID = masterMod.makeId(CrystallizationofWinter.class.getSimpleName());
    private static final int COST = 2;

    public CrystallizationofWinter() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseMagicNumber = 4;
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
        this.addToBot(new ApplyPowerAction(p, p, new BusterPerformancePower(p, this.magicNumber), this.magicNumber));
    }
}
