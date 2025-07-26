package fatemaster.cards.fgoNormal;

import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.BeastMagicPower;

public class BeastMagic extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/BeastMagic.png";
    public static final String ID = masterMod.makeId(BeastMagic.class.getSimpleName());
    private static final int COST = 1;

    public BeastMagic() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
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
        this.addToBot(new ApplyPowerAction(p, p, new BeastMagicPower(p, this.magicNumber), this.magicNumber));
    }
}
