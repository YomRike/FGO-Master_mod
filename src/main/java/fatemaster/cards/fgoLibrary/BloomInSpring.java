package fatemaster.cards.fgoLibrary;

import fatemaster.Action.BloomInSpringAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;

public class BloomInSpring extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/BloomInSpring.png";
    public static final String ID = masterMod.makeId(BloomInSpring.class.getSimpleName());
    private static final int COST = -1;

    public BloomInSpring() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.selfRetain = true;
            this.rawDescription = (getCardStrings(ID)).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new BloomInSpringAction(p, this.freeToPlayOnce, this.energyOnUse));
    }
}
