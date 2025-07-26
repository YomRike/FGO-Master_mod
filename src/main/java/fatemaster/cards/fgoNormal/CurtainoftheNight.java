package fatemaster.cards.fgoNormal;

import fatemaster.Action.MeditateFateAction;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

public class CurtainoftheNight extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/CurtainoftheNight.png";
    public static final String ID = masterMod.makeId(CurtainoftheNight.class.getSimpleName());
    private static final int COST = 1;

    public CurtainoftheNight() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.rawDescription = (getCardStrings(ID)).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MeditateFateAction(this.magicNumber, true));
    }
}
