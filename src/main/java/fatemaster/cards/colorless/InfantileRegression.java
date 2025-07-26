package fatemaster.cards.colorless;

import fatemaster.cards.fgoMasterBase;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.InfiniteGrowthPower;

public class InfantileRegression extends fgoMasterBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/InfantileRegression.png";
    public static final String ID = masterMod.makeId(InfantileRegression.class.getSimpleName());
    private static final int COST = 0;

    public InfantileRegression() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.selfRetain = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(-1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //this.addToBot(new RemoveSpecificPowerAction(p, p, HugeScalePower.POWER_ID));
        if (p.hasPower(InfiniteGrowthPower.POWER_ID)) {
            int MaxHPAmt = p.getPower(InfiniteGrowthPower.POWER_ID).amount;
            if (MaxHPAmt >= this.magicNumber) {
                this.addToBot(new GainEnergyAction(MaxHPAmt / this.magicNumber));
            }
            this.addToBot(new RemoveSpecificPowerAction(p, p, InfiniteGrowthPower.POWER_ID));
        }
    }
}
