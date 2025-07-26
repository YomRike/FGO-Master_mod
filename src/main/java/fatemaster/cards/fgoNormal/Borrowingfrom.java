package fatemaster.cards.fgoNormal;

import fatemaster.Action.NP.FgoNpAction;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.characters.master;
import fatemaster.masterMod;

public class Borrowingfrom extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/Borrowingfrom.png";
    public static final String ID = masterMod.makeId(Borrowingfrom.class.getSimpleName());
    private static final int COST = 2;

    public Borrowingfrom() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new FgoNpAction(master.fgoNp));
    }
}
