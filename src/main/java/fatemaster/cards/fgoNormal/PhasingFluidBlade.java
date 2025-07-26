package fatemaster.cards.fgoNormal;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import fatemaster.masterMod;

public class PhasingFluidBlade extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/PhasingFluidBlade.png";
    public static final String ID = masterMod.makeId(PhasingFluidBlade.class.getSimpleName());
    private static final int COST = 1;

    public PhasingFluidBlade() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 7;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("ATTACK_HEAVY"));
        this.addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        
        int roll = MathUtils.random(1);
        if (roll == 0) {
            this.addToBot(new DrawPileToHandAction(this.magicNumber, CardType.ATTACK));
        } else {
            this.addToBot(new DrawPileToHandAction(this.magicNumber, CardType.SKILL));
        }
    }
}
