package fatemaster.cards.fgoNormal;

import fatemaster.Action.NP.FgoNpAction;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.characters.master;
import fatemaster.masterMod;

public class AnimalDialogue extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/AnimalDialogue.png";
    public static final String ID = masterMod.makeId(AnimalDialogue.class.getSimpleName());
    private static final int COST = 0;

    public AnimalDialogue() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
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
        this.addToBot(new GainEnergyAction(this.magicNumber));
        this.addToBot(new FgoNpAction(-master.fgoNp));
        /*if (master.fgoNp <= 30) {
            this.exhaust = true;
        }*/
    }

    /*@Override
    public void triggerOnGlowCheck() {
        if (master.fgoNp >= 20) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }*/
}
