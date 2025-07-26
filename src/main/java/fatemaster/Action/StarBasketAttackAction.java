package fatemaster.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class StarBasketAttackAction extends AbstractGameAction {
    private final int gainBlock;
    private final DamageInfo info;

    public StarBasketAttackAction(AbstractCreature target, DamageInfo info, int gainBlock) {
        this.info = info;
        this.setValues(target, info);
        this.gainBlock = gainBlock;
        this.actionType = ActionType.DAMAGE;
        this.duration = 0.1F;
    }

    @Override
    public void update() {
        if (this.duration == 0.1F && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.NONE));
            this.target.damage(this.info);
            if (this.target.currentHealth > 0) {
                this.addToBot(new GainBlockAction(this.target, AbstractDungeon.player, this.gainBlock));
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }

        this.tickDuration();
    }
}
