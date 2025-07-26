package fatemaster.cards.fgoNormal;

import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.EternalSleepPower;
import fatemaster.powers.PowerUpBoostPower;

public class EndOfADream extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/EndOfADream.png";
    public static final String ID = masterMod.makeId(EndOfADream.class.getSimpleName());
    private static final int COST = 0;

    public EndOfADream() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 1;
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
        this.addToBot(new ApplyPowerAction(p, p, new EternalSleepPower(p, 1), 1));
        this.addToBot(new ApplyPowerAction(p, p, new PowerUpBoostPower(p, this.magicNumber), this.magicNumber));
    }
}
