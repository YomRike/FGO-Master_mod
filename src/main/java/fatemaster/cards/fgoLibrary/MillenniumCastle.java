package fatemaster.cards.fgoLibrary;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;
import fatemaster.powers.MillenniumCastlePower;

public class MillenniumCastle extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/MillenniumCastle.png";
    public static final String ID = masterMod.makeId(MillenniumCastle.class.getSimpleName());
    private static final int COST = 2;

    public MillenniumCastle() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            /*this.isInnate = true;
            this.rawDescription = (getCardStrings(ID)).UPGRADE_DESCRIPTION;
            this.initializeDescription();*/
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new MillenniumCastlePower(p, this.magicNumber), this.magicNumber));
    }
}
