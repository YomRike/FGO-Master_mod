package fatemaster.cards.colorless;

import fatemaster.cards.fgoMasterBase;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.BurnDamagePower;

public class FlamesofApplause extends fgoMasterBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/FlamesofApplause.png";
    public static final String ID = masterMod.makeId(FlamesofApplause.class.getSimpleName());
    private static final int COST = 0;

    public FlamesofApplause() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 4;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(1);
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        this.addToBot(new ApplyPowerAction(m, p, new BurnDamagePower(m, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.NONE));
    }
}
