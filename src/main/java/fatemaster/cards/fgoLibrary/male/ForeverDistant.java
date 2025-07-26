package fatemaster.cards.fgoLibrary.male;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.Action.ForeverDistantAction;
import fatemaster.cards.SupportCraft.ExcaliburSword;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;

public class ForeverDistant extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/ForeverDistant.png";
    public static final String ID = masterMod.makeId(ForeverDistant.class.getSimpleName());
    private static final int COST = 0;

    public ForeverDistant() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new ExcaliburSword();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            //this.upgradeBaseCost(0);
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ForeverDistantAction(p, p, this.magicNumber));
    }
}
