package fatemaster.cards.fgoLibrary;

import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.AtTheWellPower;
import fatemaster.powers.GutsPower;

public class AtTheWell extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/AtTheWell.png";
    public static final String ID = masterMod.makeId(AtTheWell.class.getSimpleName());
    private static final int COST = 0;

    public AtTheWell() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 9;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        /*this.addToBot(new RemoveAllPowersAction(p, true));
        if (this.upgraded) {
            this.addToBot(new HealAction(p, p, this.magicNumber));
        }*/
        this.addToBot(new ApplyPowerAction(p, p, new GutsPower(p, p.currentHealth - this.magicNumber), p.currentHealth - this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new AtTheWellPower(p, this.magicNumber), this.magicNumber));
    }
}
