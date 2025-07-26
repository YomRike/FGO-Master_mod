package fatemaster.cards.fgoNormal;

import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

public class GiantoftheBridge extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/GiantoftheBridge.png";
    public static final String ID = masterMod.makeId(GiantoftheBridge.class.getSimpleName());
    private static final int COST = 3;

    public GiantoftheBridge() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseBlock = 20;
        this.baseDamage = 15;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(5);
            this.upgradeBlock(5);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        if (AbstractDungeon.actNum == 2 || AbstractDungeon.actNum >= 4) {
            this.addToBot(new GainBlockAction(p, p, this.block));
        }

        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        if (AbstractDungeon.actNum == 2 || AbstractDungeon.actNum >= 4) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }
    }

    @Override
    public void applyPowers() {
        if (AbstractDungeon.actNum == 2 || AbstractDungeon.actNum >= 4) {
            this.rawDescription = (getCardStrings(ID)).EXTENDED_DESCRIPTION[0];
        }
        super.applyPowers();
        this.initializeDescription();
    }

    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.actNum == 2 || AbstractDungeon.actNum >= 4) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}
