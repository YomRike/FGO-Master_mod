package fatemaster.cards.fgoLibrary.male;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;
import fatemaster.util.FgoMasterHelper;

public class DerFreischutz extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/DerFreischutz.png";
    public static final String ID = masterMod.makeId(DerFreischutz.class.getSimpleName());
    private static final int COST = 1;

    public DerFreischutz() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 6;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
    }

    @Override
    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        if (FgoMasterHelper.DerFreischutzThisCombat == 6) {
            this.baseDamage = 666;
            this.rawDescription = (getCardStrings(ID)).EXTENDED_DESCRIPTION[3];
        } else if (FgoMasterHelper.DerFreischutzThisCombat < 6) {
            this.rawDescription = (getCardStrings(ID)).DESCRIPTION + (getCardStrings(ID)).EXTENDED_DESCRIPTION[0] + FgoMasterHelper.DerFreischutzThisCombat + (getCardStrings(ID)).EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription = (getCardStrings(ID)).DESCRIPTION + (getCardStrings(ID)).EXTENDED_DESCRIPTION[2];
        }
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
        this.initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        if (FgoMasterHelper.DerFreischutzThisCombat == 7) {
            this.baseDamage = 666;
        }
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //this.applyPowers();
        FgoMasterHelper.DerFreischutzThisCombat++;
        this.damage = FgoMasterHelper.DerFreischutzThisCombat == 6 ? 666 : this.damage;
        this.calculateCardDamage(m);
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        this.rawDescription = (getCardStrings(ID)).DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = (getCardStrings(ID)).DESCRIPTION;
        this.initializeDescription();
    }
}
