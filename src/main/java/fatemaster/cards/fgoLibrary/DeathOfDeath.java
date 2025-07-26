package fatemaster.cards.fgoLibrary;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;
import fatemaster.powers.DeathOfDeathPower;
import fatemaster.powers.GutsPower;

public class DeathOfDeath extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/DeathOfDeath.png";
    public static final String ID = masterMod.makeId(DeathOfDeath.class.getSimpleName());
    private static final int COST = 3;

    public DeathOfDeath() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 25;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.tags.add(CardTags.HEALING);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(15);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new DeathOfDeathPower(p, this.magicNumber), this.magicNumber));
        //this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
    }
}
