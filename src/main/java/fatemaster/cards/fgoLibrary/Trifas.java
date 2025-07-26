package fatemaster.cards.fgoLibrary;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.Action.TrifasAction;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;

public class Trifas extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/Trifas.png";
    public static final String ID = masterMod.makeId(Trifas.class.getSimpleName());
    private static final int COST = 1;

    public Trifas() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        /*this.baseBlock = 12;*/
        this.exhaust = true;
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
        /*this.addToBot(new GainBlockAction(p, p, this.block));
        if (p.hasPower("Weakened")) {
            this.addToBot(new RemoveSpecificPowerAction(p, p, "Weakened"));
        }*/
        this.addToBot(new TrifasAction(p, p));
    }
}
