package fatemaster.cards.fgoNormal;

import fatemaster.Action.BlessedScionAction;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

public class BlessedScion extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/BlessedScion.png";
    public static final String ID = masterMod.makeId(BlessedScion.class.getSimpleName());
    private static final int COST = 1;

    public BlessedScion() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.portraitImg = ImageMaster.loadImage(IMG_PATH);
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
        this.addToBot(new BlessedScionAction(p, p, this.magicNumber, true));
    }
}
