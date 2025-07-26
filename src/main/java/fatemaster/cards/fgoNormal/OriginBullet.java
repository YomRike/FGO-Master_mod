package fatemaster.cards.fgoNormal;

import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import fatemaster.masterMod;

public class OriginBullet extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/OriginBullet.png";
    public static final String ID = masterMod.makeId(OriginBullet.class.getSimpleName());
    private static final int COST = 2;

    public OriginBullet() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 10;
        this.baseMagicNumber = 5;
        this.magicNumber = this.baseMagicNumber;
        this.selfRetain = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(5);
            //this.upgradeMagicNumber(2);
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int BulletAmt = 0;
        for (AbstractPower ignored : mo.powers) {
            ++BulletAmt;
        }

        int realBaseDamage = this.baseDamage;
        this.baseDamage += BulletAmt * this.magicNumber;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("fatemaster:GUN2"));
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
    }
}
