package fatemaster.Action.NP;

import fatemaster.characters.master;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.TextAboveCreatureEffect;
import fatemaster.powers.SealNPPower;

public class FgoNpAction extends AbstractGameAction {
    private final int amount;
    private final boolean canText;
    public FgoNpAction(int amount) {
        this(amount, false);
    }

    public FgoNpAction(int amount, boolean canText) {
        this.amount = amount;
        this.canText = canText;
        /*this.actionType = ActionType.SPECIAL;
        this.duration = this.startDuration = Settings.ACTION_DUR_XFAST;*/
    }

    @Override
    public void update() {
        if (AbstractDungeon.player.hasPower(SealNPPower.POWER_ID)) {
            this.isDone = true;
            return;
        }

        master.fgoNp += this.amount;
        if (master.fgoNp > 300) {
            master.fgoNp = 300;
        }
        if (master.fgoNp < 0) {
            master.fgoNp = 0;
        }

        if (this.canText && this.amount != 0) {
            String text = this.amount > 0 ? "NP+" + this.amount + "%" : "NP" + this.amount + "%";
            AbstractDungeon.effectList.add(new TextAboveCreatureEffect(
                    AbstractDungeon.player.hb.cX - AbstractDungeon.player.animX,
                    AbstractDungeon.player.hb.cY + AbstractDungeon.player.hb.height / 2.0F,
                    text,
                    Color.WHITE.cpy()));
        }

        if (master.fgoNp == 99) {
            ++master.fgoNp;
        }

        if (AbstractDungeon.player instanceof master) {
            ((master)AbstractDungeon.player).TruthValueUpdatedEvent();
        }

        this.isDone = true;
    }
}
