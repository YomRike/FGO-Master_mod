package fatemaster.cards.fgoLibrary;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;

public class RepayForLife extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/RepayForLife.png";
    public static final String ID = masterMod.makeId(RepayForLife.class.getSimpleName());
    private static final int COST = 2;

    public RepayForLife() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 0;
        this.exhaust = true;
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
        this.addToBot(new GainBlockAction(p, p, this.block));
        this.initializeDescription();
    }

    @Override
    public void applyPowers() {
        this.baseBlock = AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth;

        super.applyPowers();
        this.rawDescription = (getCardStrings(ID)).DESCRIPTION + (getCardStrings(ID)).EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }
}
