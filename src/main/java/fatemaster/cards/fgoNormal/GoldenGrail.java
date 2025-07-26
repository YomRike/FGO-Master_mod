package fatemaster.cards.fgoNormal;

import fatemaster.Action.GoldenGrailAction;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

public class GoldenGrail extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/GoldenGrail.png";
    public static final String ID = masterMod.makeId(GoldenGrail.class.getSimpleName());
    private static final int COST = -1;

    public GoldenGrail() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        //this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = (getCardStrings(ID)).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GoldenGrailAction(p, this.freeToPlayOnce, this.energyOnUse, this.upgraded, this.magicNumber));
        //this.addToBot(new SevenBeastCrownsAction(p, this.freeToPlayOnce, this.energyOnUse));
    }
}
