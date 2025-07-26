package fatemaster.cards.colorless;

import fatemaster.Enum.AbstractCardEnum;
import fatemaster.cards.fgoMasterBase;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

public class BlackBarrel extends fgoMasterBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/BlackBarrel.png";
    public static final String ID = masterMod.makeId(BlackBarrel.class.getSimpleName());
    private static final int COST = 2;

    public BlackBarrel() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, AbstractCardEnum.Other_COLOR, CardRarity.BASIC, CardTarget.ENEMY);
        this.baseDamage = 9;
        //this.exhaust = true;
        this.selfRetain = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(5);
            //this.upgradeBaseCost(1);
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber;
        if (!mo.isDying && !mo.isDead && mo.currentBlock > 0) {
            this.baseDamage += mo.currentBlock;
        }
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }
}
