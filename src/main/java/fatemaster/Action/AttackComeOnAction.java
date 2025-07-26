package fatemaster.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.powers.BurnDamagePower;

public class AttackComeOnAction extends AbstractGameAction {
    private final AbstractCard card;
    private final AbstractGameAction.AttackEffect effect;

    public AttackComeOnAction(AbstractCard card, AbstractGameAction.AttackEffect effect) {
        this.card = card;
        this.effect = effect;
    }

    @Override
    public void update() {
        this.target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        if (this.target != null) {
            this.card.calculateCardDamage((AbstractMonster)this.target);
            this.addToTop(new DamageAction(this.target, new DamageInfo(AbstractDungeon.player, this.card.damage, this.card.damageTypeForTurn), this.effect));
            if (this.target.hasPower(BurnDamagePower.POWER_ID)) {
                int BurnAmt = this.target.getPower(BurnDamagePower.POWER_ID).amount;
                this.addToTop(new ApplyPowerAction(this.target, this.source, new BurnDamagePower(this.target, BurnAmt), BurnAmt));
            }
        }

        this.isDone = true;
    }
}
