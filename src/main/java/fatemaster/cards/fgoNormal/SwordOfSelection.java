package fatemaster.cards.fgoNormal;

import fatemaster.Action.NP.FgoNpAction;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

public class SwordOfSelection extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/SwordOfSelection.png";
    public static final String ID = masterMod.makeId(SwordOfSelection.class.getSimpleName());
    private static final int COST = 1;

    public SwordOfSelection() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int theSize = AbstractDungeon.player.hand.size();
        this.addToBot(new FgoNpAction(theSize * 2, true));
        //this.addToBot(new ApplyPowerAction(p, p, new ArtsPerformancePower(p, 2), 2));
        this.addToBot(new DrawCardAction(p, this.magicNumber));
    }

    @Override
    public void applyPowers() {
        int theSize = AbstractDungeon.player.hand.size();
        this.rawDescription = (getCardStrings(ID)).DESCRIPTION + (getCardStrings(ID)).EXTENDED_DESCRIPTION[0] + theSize * 2 + (getCardStrings(ID)).EXTENDED_DESCRIPTION[1];
        super.applyPowers();
        this.initializeDescription();
    }
}
