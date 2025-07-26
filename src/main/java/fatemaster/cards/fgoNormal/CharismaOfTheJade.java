package fatemaster.cards.fgoNormal;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import fatemaster.masterMod;

public class CharismaOfTheJade extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/CharismaOfTheJade.png";
    public static final String ID = masterMod.makeId(CharismaOfTheJade.class.getSimpleName());
    private static final int COST = 3;

    public CharismaOfTheJade() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 8;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
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
        if (m != null) {
            this.addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
        }

        this.addToBot(new WaitAction(0.8F));
        if (m != null) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
    }

    @Override
    public void applyPowers() {
        AbstractPower vigor = AbstractDungeon.player.getPower(VigorPower.POWER_ID);
        if (vigor != null) {
            vigor.amount *= this.magicNumber;
        }

        super.applyPowers();
        if (vigor != null) {
            vigor.amount /= this.magicNumber;
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower vigor = AbstractDungeon.player.getPower(VigorPower.POWER_ID);
        if (vigor != null) {
            vigor.amount *= this.magicNumber;
        }

        super.calculateCardDamage(mo);
        if (vigor != null) {
            vigor.amount /= this.magicNumber;
        }
    }
}
