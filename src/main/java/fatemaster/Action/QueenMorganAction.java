package fatemaster.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class QueenMorganAction extends AbstractGameAction {
    public QueenMorganAction() {
    }

    @Override
    public void update() {
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power.type == AbstractPower.PowerType.BUFF) {
                this.addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, power));
                break;
            }
        }

        this.isDone = true;
    }
}
