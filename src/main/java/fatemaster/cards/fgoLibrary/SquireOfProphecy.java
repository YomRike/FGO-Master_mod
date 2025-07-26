package fatemaster.cards.fgoLibrary;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;
import fatemaster.powers.SquireOfProphecyPower;

public class SquireOfProphecy extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/SquireOfProphecy.png";
    public static final String ID = masterMod.makeId(SquireOfProphecy.class.getSimpleName());
    private static final int COST = 0;

    public SquireOfProphecy() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        //this.exhaust = true;
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
        this.addToBot(new ApplyPowerAction(p, p, new SquireOfProphecyPower(p, this.magicNumber), this.magicNumber));
    }
}
