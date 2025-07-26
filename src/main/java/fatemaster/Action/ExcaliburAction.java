package fatemaster.Action;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.CardPoofEffect;
import fatemaster.modifier.ExcaliburModifier;
import org.apache.logging.log4j.util.Supplier;

public class ExcaliburAction extends AbstractGameAction {
    private final AbstractCard card;
    private final Supplier<AbstractCard> replacement;

    public ExcaliburAction(AbstractCard card, Supplier<AbstractCard> replacement) {
        this.card = card;
        this.replacement = replacement;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
    }

    public ExcaliburAction(AbstractCard card, AbstractCard replacement) {
        this(card, () -> replacement);
    }

    @Override
    public void update() {
        if (!this.isDone) {
            CardGroup group = null;
            if (AbstractDungeon.player.hand.contains(this.card)) {
                group = AbstractDungeon.player.hand;
            } else if (AbstractDungeon.player.drawPile.contains(this.card)) {
                group = AbstractDungeon.player.drawPile;
            } else if (AbstractDungeon.player.discardPile.contains(this.card)) {
                group = AbstractDungeon.player.discardPile;
            } else if (AbstractDungeon.player.exhaustPile.contains(this.card)) {
                group = AbstractDungeon.player.exhaustPile;
            }
            if (group == null) {
                this.isDone = true;
                return;
            }
            AbstractCard rep = this.replacement.get();
            this.duration = 1.5F;
            rep.current_x = this.card.current_x;
            rep.current_y = this.card.current_y;
            rep.target_x = this.card.target_x;
            rep.target_y = this.card.target_y;
            AbstractDungeon.effectsQueue.add(new CardPoofEffect(this.card.target_x, this.card.target_y));
            this.card.drawScale = 0.01F;
            this.card.targetDrawScale = 1.0F;
            rep.angle = this.card.angle;

            CardCrawlGame.sound.play("CARD_OBTAIN");
            CardModifierManager.addModifier(rep, new ExcaliburModifier());

            int index = group.group.indexOf(this.card);
            group.group.set(index, rep);

            if (group.type == CardGroup.CardGroupType.HAND) {
                rep.flash();
            }

            this.isDone = true;
        }
    }
}
