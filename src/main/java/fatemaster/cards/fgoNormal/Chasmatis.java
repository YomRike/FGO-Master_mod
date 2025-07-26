package fatemaster.cards.fgoNormal;

import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.ChasmatisPower;

public class Chasmatis extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/Chasmatis.png";
    public static final String ID = masterMod.makeId(Chasmatis.class.getSimpleName());
    private static final int COST = 1;

    public Chasmatis() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new ChasmatisPower(p, this.magicNumber), this.magicNumber));
    }
}
