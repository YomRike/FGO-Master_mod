package fatemaster.cards.fgoLibrary;

import fatemaster.cards.colorless.InfantileRegression;
import fatemaster.cards.fgoLibraryBase;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.HugeScalePower;

public class HugeScale extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/HugeScale.png";
    public static final String ID = masterMod.makeId(HugeScale.class.getSimpleName());
    private static final int COST = 1;

    public HugeScale() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new InfantileRegression();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new HugeScalePower(p, this.magicNumber), this.magicNumber));
        this.addToTop(new MakeTempCardInHandAction(this.cardsToPreview, 1));
        //this.addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, 1), 1));
    }
}
