package fatemaster.cards.fgoNormal;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.cards.fgoNormalCard;
import fatemaster.masterMod;
import fatemaster.powers.CrossingArcadiaPower;

public class CrossingArcadia extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/CriticalScope.png";
    public static final String ID = masterMod.makeId(CrossingArcadia.class.getSimpleName());
    private static final int COST = 1;

    public CrossingArcadia() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 5;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
            //this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        this.addToBot(new ApplyPowerAction(p, p, new CrossingArcadiaPower(p, this.magicNumber), this.magicNumber));
    }
}
