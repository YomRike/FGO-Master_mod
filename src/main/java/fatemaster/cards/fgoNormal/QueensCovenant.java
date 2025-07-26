package fatemaster.cards.fgoNormal;

import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import fatemaster.masterMod;
import fatemaster.powers.MyFairSoldierPower;

public class QueensCovenant extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/QueensCovenant.png";
    public static final String ID = masterMod.makeId(QueensCovenant.class.getSimpleName());
    private static final int COST = 2;

    public QueensCovenant() {
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
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new MyFairSoldierPower(p, this.magicNumber), this.magicNumber));
    }
}
