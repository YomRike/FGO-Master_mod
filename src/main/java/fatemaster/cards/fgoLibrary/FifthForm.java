package fatemaster.cards.fgoLibrary;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;
import fatemaster.powers.FifthFormPower;

public class FifthForm extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/FifthForm.png";
    public static final String ID = masterMod.makeId(FifthForm.class.getSimpleName());
    private static final int COST = 1;

    public FifthForm() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.portraitImg = ImageMaster.loadImage(IMG_PATH);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
            /*this.rawDescription = (getCardStrings(ID)).UPGRADE_DESCRIPTION;
            this.initializeDescription();*/
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new FifthFormPower(p, 1), 1));
    }
}
