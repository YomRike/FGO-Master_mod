package fatemaster.cards.fgoNormal;

import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.MonteCristoTreasurePower;

public class MonteCristoTreasure extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/MonteCristoTreasure.png";
    public static final String ID = masterMod.makeId(MonteCristoTreasure.class.getSimpleName());
    private static final int COST = 3;

    public MonteCristoTreasure() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new MonteCristoTreasurePower(p, this.magicNumber), this.magicNumber));
    }
}
