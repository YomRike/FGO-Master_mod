package fatemaster.cards.fgoNormal;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
import fatemaster.cards.fgoNormalCard;
import fatemaster.masterMod;

public class CharismaofAdversity extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/CharismaofAdversity.png";
    public static final String ID = masterMod.makeId(CharismaofAdversity.class.getSimpleName());
    private static final int COST = 2;

    public CharismaofAdversity() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 6;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            this.addToBot(new VFXAction(new VerticalImpactEffect(m.hb.cX + m.hb.width / 4.0F, m.hb.cY - m.hb.height / 4.0F)));
        }

        int lostHealthPercentage = p.maxHealth - p.currentHealth;
        int extraHits = lostHealthPercentage / 12;

        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        if (extraHits >= 1) {
            for (int i = 0; i < extraHits; i++) {
                this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            }
        }
    }

    @Override
    public void applyPowers() {
        int lostHealthPercentage = AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth;
        int extraHits = lostHealthPercentage / 12;
        if (extraHits >= 1) {
            this.rawDescription = (getCardStrings(ID)).DESCRIPTION + (getCardStrings(ID)).EXTENDED_DESCRIPTION[0] + extraHits + (getCardStrings(ID)).EXTENDED_DESCRIPTION[1];
        }
        super.applyPowers();
        this.initializeDescription();
    }

    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth >= 12) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}
