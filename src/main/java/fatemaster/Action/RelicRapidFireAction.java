package fatemaster.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import fatemaster.vfx.RelicRapidFireEffect;


public class RelicRapidFireAction extends AbstractGameAction {
    private final RelicRapidFireEffect rife;

    public RelicRapidFireAction(AbstractMonster target, AbstractRelic relic, int time) {
        this.actionType = ActionType.SPECIAL;

        this.rife = new RelicRapidFireEffect(relic, target, time);
        AbstractDungeon.effectList.add(this.rife);
    }

    public RelicRapidFireAction(AbstractMonster target, AbstractRelic relic) {
        this.actionType = ActionType.SPECIAL;

        this.rife = new RelicRapidFireEffect(relic, target);
        AbstractDungeon.effectList.add(this.rife);
    }

    public void update() {
        if (this.rife.finishedAction)
            this.isDone = true;
    }
}