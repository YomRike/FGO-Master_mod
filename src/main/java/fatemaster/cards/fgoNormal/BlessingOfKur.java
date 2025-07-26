package fatemaster.cards.fgoNormal;

import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.BlessingOfKurPower;
import fatemaster.powers.CursePower;
import fatemaster.powers.MaxHPPower;

public class BlessingOfKur extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/BlessingOfKur.png";
    public static final String ID = masterMod.makeId(BlessingOfKur.class.getSimpleName());
    private static final int COST = 1;

    public BlessingOfKur() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        this.baseMagicNumber = 15;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(5);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //this.addToBot(new ApplyPowerAction(p, p, new MaxHPPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new BlessingOfKurPower(p, this.magicNumber), this.magicNumber));
    }
}
