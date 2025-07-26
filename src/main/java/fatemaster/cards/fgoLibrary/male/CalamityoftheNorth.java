package fatemaster.cards.fgoLibrary.male;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;
import fatemaster.powers.CalamityoftheNorthPower;

public class CalamityoftheNorth extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/CalamityoftheNorth.png";
    public static final String ID = masterMod.makeId(CalamityoftheNorth.class.getSimpleName());
    private static final int COST = 0;

    public CalamityoftheNorth() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 3;
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
        this.addToBot(new ApplyPowerAction(p, p, new CalamityoftheNorthPower(p, this.magicNumber), this.magicNumber));
    }
}
