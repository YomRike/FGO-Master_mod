package fatemaster.cards.fgoNormal;

import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.GoddessMetamorphosisBeastPower;


public class GoddessMetamorphosisBeast extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/GoddessMetamorphosisBeast.png";
    public static final String ID = masterMod.makeId(GoddessMetamorphosisBeast.class.getSimpleName());
    private static final int COST = 2;

    public GoddessMetamorphosisBeast() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.isEthereal = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isEthereal = false;
            this.rawDescription = (getCardStrings(ID)).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new GoddessMetamorphosisBeastPower(p, this.magicNumber), this.magicNumber));
    }
}
