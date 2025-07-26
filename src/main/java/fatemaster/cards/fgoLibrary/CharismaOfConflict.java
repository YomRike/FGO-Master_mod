package fatemaster.cards.fgoLibrary;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;
import fatemaster.powers.BlackSunPower;
import fatemaster.powers.SunlightNullificationPower;

public class CharismaOfConflict extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/BlackSun.png";
    public static final String ID = masterMod.makeId(CharismaOfConflict.class.getSimpleName());
    private static final int COST = 1;

    public CharismaOfConflict() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
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
        this.addToBot(new ApplyPowerAction(p, p, new BlackSunPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new SunlightNullificationPower(p, this.magicNumber), this.magicNumber));
    }
}
