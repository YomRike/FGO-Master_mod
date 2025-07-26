package fatemaster.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.FrozenEye;
import fatemaster.powers.ViyViyViyPower;

import java.util.ArrayList;

public class ViyViyViyAction extends AbstractGameAction {
    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        ArrayList<AbstractRelic> rs = new ArrayList<>();

        if (!p.hasRelic("Frozen Eye")) {
            FrozenEye frozenEye = new FrozenEye();
            rs.add(frozenEye);
        }

        if (rs.isEmpty()) {
            this.isDone = true;
            return;
        }

        AbstractRelic abstractRelic = rs.get(0);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ViyViyViyPower(p, abstractRelic)));

        this.isDone = true;
    }
}