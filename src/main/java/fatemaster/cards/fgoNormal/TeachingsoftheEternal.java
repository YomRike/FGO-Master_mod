package fatemaster.cards.fgoNormal;

import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import fatemaster.masterMod;

public class TeachingsoftheEternal extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/TeachingsoftheEternal.png";
    public static final String ID = masterMod.makeId(TeachingsoftheEternal.class.getSimpleName());
    private static final int COST = 1;

    public TeachingsoftheEternal() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 9;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        this.addToBot(new ApplyPowerAction(p, p, new BlurPower(p, 1), 1));
    }
}
