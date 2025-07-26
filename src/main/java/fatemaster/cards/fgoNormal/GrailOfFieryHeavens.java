package fatemaster.cards.fgoNormal;

import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.EnergyRegenPower;

public class GrailOfFieryHeavens extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/DesiresSalvation.png";
    public static final String ID = masterMod.makeId(GrailOfFieryHeavens.class.getSimpleName());
    private static final int COST = 1;

    public GrailOfFieryHeavens() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 10;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = (getCardStrings(ID)).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new EnergyRegenPower(p, this.magicNumber), this.magicNumber));
    }
}
