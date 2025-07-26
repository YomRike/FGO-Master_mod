package fatemaster.cards.fgoLibrary;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;

public class HeroEli extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/HeroEli.png";
    public static final String ID = masterMod.makeId(HeroEli.class.getSimpleName());
    private static final int COST = 2;

    public HeroEli() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 10;
        this.baseMagicNumber = 1;
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
        int attackAmt = 0;
        int roll = MathUtils.random(2);
        if (roll == 0) {
            attackAmt += 3;
        } else if (roll == 1) {
            attackAmt += 2;
        } else {
            attackAmt += 1;
        }

        this.addToBot(new TalkAction(true, String.valueOf(attackAmt), 2.5F, 2.5F));
        for (int i = 0; i < attackAmt; ++i) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            this.addToBot(new DrawCardAction(p, this.magicNumber));
        }
    }
}
