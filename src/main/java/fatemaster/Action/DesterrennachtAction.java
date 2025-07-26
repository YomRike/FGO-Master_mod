package fatemaster.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import fatemaster.Enum.CardTagsEnum;

public class DesterrennachtAction extends AbstractGameAction {
    public DesterrennachtAction() {
        this.duration = Settings.ACTION_DUR_MED;
        this.actionType = ActionType.WAIT;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            AbstractPlayer p = AbstractDungeon.player;
            this.upgradeAllCardsInGroup(p.hand);
            this.upgradeAllCardsInGroup(p.drawPile);
            this.upgradeAllCardsInGroup(p.discardPile);
            this.upgradeAllCardsInGroup(p.exhaustPile);
            this.isDone = true;
        }

    }

    private void upgradeAllCardsInGroup(CardGroup cardGroup) {
        for (AbstractCard c : cardGroup.group) {
            if (c.canUpgrade() && c.hasTag(CardTagsEnum.Foreigner)) {
                if (cardGroup.type == CardGroup.CardGroupType.HAND) {
                    c.superFlash();
                }

                c.upgrade();
                c.applyPowers();
            }
        }

    }
}
