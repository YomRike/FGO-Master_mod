package fatemaster.cards.fgoNormal;

import com.megacrit.cardcrawl.powers.EnergizedPower;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.StarGainPower;

public class DreamUponTheStars extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/DreamUponTheStars.png";
    public static final String ID = masterMod.makeId(DreamUponTheStars.class.getSimpleName());
    private static final int COST = 2;

    public DreamUponTheStars() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        this.baseDamage = 12;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.rawDescription = (getCardStrings(ID)).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.addToBot(new ApplyPowerAction(p, p, new EnergizedPower(p, this.magicNumber), this.magicNumber));
    }
}
