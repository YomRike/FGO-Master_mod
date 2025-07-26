package fatemaster.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class GodsExecutionAction extends AbstractGameAction {
    private final DamageInfo info;
    public GodsExecutionAction(AbstractCreature target, DamageInfo info) {
        this.actionType = ActionType.BLOCK;
        this.target = target;
        this.info = info;
    }

    @Override
    public void update() {
        if (this.target != null) {
            for (AbstractPower power : AbstractDungeon.player.powers) {
                if (power.type == AbstractPower.PowerType.DEBUFF) {
                    this.addToTop(new DrawCardAction(AbstractDungeon.player, 1));
                    this.addToTop(new GainEnergyAction(1));
                    break;
                }
            }
        }

        this.addToTop(new DamageAction(this.target, this.info, AttackEffect.BLUNT_HEAVY));
        this.isDone = true;
    }
}
