package fatemaster.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.WingBoots;

public class EnferChateaudIfAction extends AbstractGameAction {

    @Override
    public void update() {
        if (!AbstractDungeon.player.hasRelic(WingBoots.ID)) {
            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2), new WingBoots());
        } else if (AbstractDungeon.player.hasRelic(WingBoots.ID)) {
            AbstractDungeon.player.loseRelic(WingBoots.ID);
        }

        this.isDone = true;
    }
}
